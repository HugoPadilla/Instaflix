package com.example.instaflix.data.factory

import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import com.example.instaflix.data.factory.remoteMediators.PopularTvShowRemoteMediator
import com.example.instaflix.data.local.MoviesDatabase
import com.example.instaflix.data.local.entity.TvShowEntity
import com.example.instaflix.data.remote.TVShowApiService
import javax.inject.Inject

class TvShowRemoteMediatorFactoryImpl @Inject constructor(
    private val movieApiService: TVShowApiService,
    private val moviesDatabase: MoviesDatabase,
) : TvShowRemoteMediatorFactory {
    @OptIn(ExperimentalPagingApi::class)
    override fun create(category: CategoryTvShow): RemoteMediator<Int, TvShowEntity> {
        return when(category) {
            CategoryTvShow.UPCOMING -> TODO()
            CategoryTvShow.POPULAR -> PopularTvShowRemoteMediator(tvShowApiService = movieApiService, moviesDatabase)
            CategoryTvShow.TOP_RATED -> TODO()
        }
    }
}