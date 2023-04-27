package com.example.instaflix.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.instaflix.data.factory.CategoryMovies
import com.example.instaflix.data.factory.RemoteMediatorFactory
import com.example.instaflix.data.local.MoviesDatabase
import com.example.instaflix.data.local.entity.MovieEntity
import com.example.instaflix.domain.repository.MovieRepository
import com.example.instaflix.utils.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteMediatorFactory: RemoteMediatorFactory,
    private val moviesDatabase: MoviesDatabase,
) : MovieRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPopularMovies(): Flow<PagingData<MovieEntity>> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = 10,
                initialLoadSize = PAGE_SIZE, // How many items you want to load initially
            ),
            pagingSourceFactory = {
                // The pagingSourceFactory lambda should always return a brand new PagingSource
                // when invoked as PagingSource instances are not reusable.
                moviesDatabase.getMoviesDao().getMovies(1)
            },
            remoteMediator = remoteMediatorFactory.create(CategoryMovies.POPULAR)
        ).flow

    @OptIn(ExperimentalPagingApi::class)
    override fun getTopRatedMovies(): Flow<PagingData<MovieEntity>> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = 10,
                initialLoadSize = PAGE_SIZE, // How many items you want to load initially
            ),
            pagingSourceFactory = {
                // The pagingSourceFactory lambda should always return a brand new PagingSource
                // when invoked as PagingSource instances are not reusable.
                moviesDatabase.getMoviesDao().getMovies(2)
            },
            remoteMediator = remoteMediatorFactory.create(CategoryMovies.TOP_RATED)
        ).flow

}