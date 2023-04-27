package com.example.instaflix.domain.repository

import androidx.paging.PagingData
import com.example.instaflix.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<PagingData<MovieEntity>>

    fun getTopRatedMovies(): Flow<PagingData<MovieEntity>>
}