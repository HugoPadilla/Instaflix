package com.example.instaflix.data.factory

import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import com.example.instaflix.data.local.entity.MovieEntity

interface RemoteMediatorFactory {
    @OptIn(ExperimentalPagingApi::class)
    fun create(category: CategoryMovies): RemoteMediator<Int, MovieEntity>
}