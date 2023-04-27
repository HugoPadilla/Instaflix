package com.example.instaflix.domain.usecase

import com.example.instaflix.domain.models.Movie
import com.example.instaflix.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
){
    suspend operator fun invoke(movieId: String): Movie {
        return movieRepository.getMovieById(movieId)
    }
}