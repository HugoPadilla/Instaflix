package com.example.instaflix.domain.usecase

import androidx.paging.PagingData
import com.example.instaflix.data.local.entity.MovieEntity
import com.example.instaflix.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlayingNowMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(): Flow<PagingData<MovieEntity>> =
        movieRepository.getNowPlayingMovies()
}