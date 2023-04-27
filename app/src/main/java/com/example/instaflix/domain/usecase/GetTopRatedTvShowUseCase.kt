package com.example.instaflix.domain.usecase

import androidx.paging.PagingData
import com.example.instaflix.data.local.entity.TvShowEntity
import com.example.instaflix.domain.repository.TvShowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedTvShowUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository,
) {
    operator fun invoke(): Flow<PagingData<TvShowEntity>> =
        tvShowRepository.getTopRated()
}