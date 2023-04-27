package com.example.instaflix.data.factory

import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import com.example.instaflix.data.factory.remoteMediators.PopularTvShowRemoteMediator
import com.example.instaflix.data.factory.remoteMediators.TopRatedTvShowRemoteMediator
import com.example.instaflix.data.factory.remoteMediators.UpcomingTvShowRemoteMediator
import com.example.instaflix.data.local.MoviesDatabase
import com.example.instaflix.data.local.entity.TvShowEntity
import com.example.instaflix.data.remote.TVShowApiService
import javax.inject.Inject

class TvShowRemoteMediatorFactoryImpl @Inject constructor(
    private val tvShowApiService: TVShowApiService,
    private val moviesDatabase: MoviesDatabase,
) : TvShowRemoteMediatorFactory {
    @OptIn(ExperimentalPagingApi::class)
    override fun create(category: CategoryTvShow): RemoteMediator<Int, TvShowEntity> {
        return when(category) {
            CategoryTvShow.UPCOMING -> UpcomingTvShowRemoteMediator(tvShowApiService, moviesDatabase)
            CategoryTvShow.POPULAR -> PopularTvShowRemoteMediator(tvShowApiService, moviesDatabase)
            CategoryTvShow.TOP_RATED -> TopRatedTvShowRemoteMediator(tvShowApiService, moviesDatabase)
        }
    }
}