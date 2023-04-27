package com.example.instaflix.data.factory.remoteMediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.instaflix.data.local.MoviesDatabase
import com.example.instaflix.data.local.entity.TvShowCategoryEntity
import com.example.instaflix.data.local.entity.TvShowEntity
import com.example.instaflix.data.local.entity.TvShowRemoteKeys
import com.example.instaflix.data.mappers.toEntity
import com.example.instaflix.data.remote.TVShowApiService
import com.example.instaflix.utils.Constants
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * RemoteMediator acts as a signal from the Paging library when the app has run out of cached data.
 * You can use this signal to load additional data from the network and store it in the local database,
 * where a PagingSource can load it and provide it to the UI to display.
 */
@OptIn(ExperimentalPagingApi::class)
class UpcomingTvShowRemoteMediator (
    private val tvShowApiService: TVShowApiService,
    private val moviesDatabase: MoviesDatabase,
): RemoteMediator<Int, TvShowEntity>() {
    /**
     * When additional data is needed, the Paging library calls the load() method from the RemoteMediator implementation.
     * This function typically fetches the new data from a network source and saves it to local storage.
     * Over time the data stored in the database requires invalidation, such as when the user manually triggers a refresh.
     * This is represented by the LoadType property passed to the load() method.
     * The LoadType informs the RemoteMediator whether it needs to refresh the existing data or fetch additional data that needs to be appended or prepended to the existing list.
     */

    /**
     * In cases where the local data needs to be fully refreshed, initialize() should return InitializeAction.LAUNCH_INITIAL_REFRESH.
     * This causes the RemoteMediator to perform a remote refresh to fully reload the data.
     *
     * In cases where the local data doesn't need to be refreshed, initialize() should return InitializeAction.SKIP_INITIAL_REFRESH.
     * This causes the RemoteMediator to skip the remote refresh and load the cached data.
     */
    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)

        return if (System.currentTimeMillis() - (moviesDatabase.getTvShowRemoteKeysDao().getCreationTime(Constants.CATEGORY_UPCOMING) ?: 0) < cacheTimeout) {
            // Cached data is up-to-date, so there is no need to re-fetch
            // from the network.
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            // Need to refresh cached data from network; returning
            // LAUNCH_INITIAL_REFRESH here will also block RemoteMediator's
            // APPEND and PREPEND from running until REFRESH succeeds.
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    /** LoadType.Append
     * When we need to load data at the end of the currently loaded data set, the load parameter is LoadType.APPEND
     */
    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, TvShowEntity>): TvShowRemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { movie ->
            moviesDatabase.getTvShowRemoteKeysDao().getRemoteKeyByMovieIDFilterByCategory(movie.id, Constants.CATEGORY_UPCOMING)
        }
    }

    /** LoadType.Prepend
     * When we need to load data at the beginning of the currently loaded data set, the load parameter is LoadType.PREPEND
     */
    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, TvShowEntity>): TvShowRemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { movie ->
            moviesDatabase.getTvShowRemoteKeysDao().getRemoteKeyByMovieIDFilterByCategory(movie.id, Constants.CATEGORY_UPCOMING)
        }
    }

    /** LoadType.REFRESH
     * Gets called when it's the first time we're loading data, or when PagingDataAdapter.refresh() is called;
     * so now the point of reference for loading our data is the state.anchorPosition.
     * If this is the first load, then the anchorPosition is null.
     * When PagingDataAdapter.refresh() is called, the anchorPosition is the first visible position in the displayed list, so we will need to load the page that contains that specific item.
     */
    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, TvShowEntity>): TvShowRemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                moviesDatabase.getTvShowRemoteKeysDao().getRemoteKeyByMovieIDFilterByCategory(id, Constants.CATEGORY_UPCOMING)
            }
        }
    }

    /**.
     *
     * @param state This gives us information about the pages that were loaded before,
     * the most recently accessed index in the list, and the PagingConfig we defined when initializing the paging stream.
     * @param loadType this tells us whether we need to load data at the end (LoadType.APPEND)
     * or at the beginning of the data (LoadType.PREPEND) that we previously loaded,
     * or if this the first time we're loading data (LoadType.REFRESH).
     */
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TvShowEntity>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                //New Query so clear the DB
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                // If remoteKeys is null, that means the refresh result is not in the database yet.
                val prevKey = remoteKeys?.prevKey
                prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)

                // If remoteKeys is null, that means the refresh result is not in the database yet.
                // We can return Success with endOfPaginationReached = false because Paging
                // will call this method again if RemoteKeys becomes non-null.
                // If remoteKeys is NOT NULL but its nextKey is null, that means we've reached
                // the end of pagination for append.
                val nextKey = remoteKeys?.nextKey
                nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {
            val apiResponse = tvShowApiService.getUpcoming(page = page)

            delay(1000L) //TODO For testing only!

            val movies = apiResponse.tvShowDtos
            val endOfPaginationReached = movies.isEmpty()

            moviesDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    moviesDatabase.getTvShowRemoteKeysDao().clearRemoteKeys(Constants.CATEGORY_UPCOMING)
                    moviesDatabase.getTvShowCategoryDao().clearAllTvShow(Constants.CATEGORY_UPCOMING)
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = movies.map {
                    TvShowRemoteKeys(tvShowId = it.id, categoryId = Constants.CATEGORY_UPCOMING, prevKey = prevKey, currentPage = page, nextKey = nextKey)
                }
                val tvShowCategoryEntity = movies.map {
                    TvShowCategoryEntity(tvShowId = it.id, categoryId = Constants.CATEGORY_UPCOMING, page = page)
                }

                moviesDatabase.getTvShowRemoteKeysDao().insertAll(remoteKeys)
                moviesDatabase.getTvShowDao().insertAll(movies.map { movieDto -> movieDto.toEntity(page) })
                moviesDatabase.getTvShowCategoryDao().insertAll(tvShowCategoryEntity)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: IOException) {
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            return MediatorResult.Error(error)
        }
    }
}