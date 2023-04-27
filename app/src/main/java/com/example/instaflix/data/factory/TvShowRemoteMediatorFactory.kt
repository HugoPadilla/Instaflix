package com.example.instaflix.data.factory

import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import com.example.instaflix.data.local.entity.TvShowEntity

interface TvShowRemoteMediatorFactory {
    @OptIn(ExperimentalPagingApi::class)
    fun create(category: CategoryTvShow): RemoteMediator<Int, TvShowEntity>
}