package com.example.instaflix.domain.repository

import androidx.paging.PagingData
import com.example.instaflix.data.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    fun getPopular(): Flow<PagingData<TvShowEntity>>

    fun getTopRated(): Flow<PagingData<TvShowEntity>>

    fun getNowPlaying(): Flow<PagingData<TvShowEntity>>
}