package com.example.instaflix.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.instaflix.data.factory.CategoryTvShow
import com.example.instaflix.data.factory.TvShowRemoteMediatorFactory
import com.example.instaflix.data.local.MoviesDatabase
import com.example.instaflix.data.local.entity.TvShowEntity
import com.example.instaflix.domain.repository.TvShowRepository
import com.example.instaflix.utils.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val remoteMediatorFactory: TvShowRemoteMediatorFactory,
    private val moviesDatabase: MoviesDatabase,
) : TvShowRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPopular(): Flow<PagingData<TvShowEntity>> =
        Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                prefetchDistance = 10,
                initialLoadSize = Constants.PAGE_SIZE, // How many items you want to load initially
            ),
            pagingSourceFactory = {
                // The pagingSourceFactory lambda should always return a brand new PagingSource
                // when invoked as PagingSource instances are not reusable.
                moviesDatabase.getTvShowDao().getTvShow(Constants.CATEGORY_POPULAR)
            },
            remoteMediator = remoteMediatorFactory.create(CategoryTvShow.POPULAR)
        ).flow

    @OptIn(ExperimentalPagingApi::class)
    override fun getTopRated(): Flow<PagingData<TvShowEntity>> =
        Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                prefetchDistance = 10,
                initialLoadSize = Constants.PAGE_SIZE,
            ),
            pagingSourceFactory = {
                moviesDatabase.getTvShowDao().getTvShow(Constants.CATEGORY_TOP_RATED)
            },
            remoteMediator = remoteMediatorFactory.create(CategoryTvShow.TOP_RATED)
        ).flow

    @OptIn(ExperimentalPagingApi::class)
    override fun getUpcoming(): Flow<PagingData<TvShowEntity>> =
        Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                prefetchDistance = 10,
                initialLoadSize = Constants.PAGE_SIZE,
            ),
            pagingSourceFactory = {
                moviesDatabase.getTvShowDao().getTvShow(Constants.CATEGORY_UPCOMING)
            },
            remoteMediator = remoteMediatorFactory.create(CategoryTvShow.UPCOMING)
        ).flow
}