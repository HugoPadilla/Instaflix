package com.example.instaflix.data.factory

import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import com.example.instaflix.data.factory.remoteMediators.PopularMoviesRemoteMediator
import com.example.instaflix.data.factory.remoteMediators.TopRatedMoviesRemoteMediator
import com.example.instaflix.data.local.MoviesDatabase
import com.example.instaflix.data.local.entity.MovieEntity
import com.example.instaflix.data.remote.MovieApiService
import javax.inject.Inject


class RemoteMediatorFactoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val moviesDatabase: MoviesDatabase,
) : RemoteMediatorFactory {

    @OptIn(ExperimentalPagingApi::class)
    override fun create(category: CategoryMovies): RemoteMediator<Int, MovieEntity> {
        return when (category) {
            CategoryMovies.POPULAR -> PopularMoviesRemoteMediator(movieApiService, moviesDatabase)
            CategoryMovies.TOP_RATED -> TopRatedMoviesRemoteMediator(movieApiService, moviesDatabase)
        }
    }
}