package com.example.instaflix.domain.repository

import androidx.paging.PagingData
import com.example.instaflix.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    fun getPopular(): Flow<PagingData<MovieEntity>>

    fun getTopRated(): Flow<PagingData<MovieEntity>>

    fun getNowPlaying(): Flow<PagingData<MovieEntity>>
}