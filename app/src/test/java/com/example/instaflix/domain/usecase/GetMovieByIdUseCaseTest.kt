package com.example.instaflix.domain.usecase

import com.example.instaflix.domain.models.Movie
import com.example.instaflix.domain.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

val movies = listOf<Movie>(
    Movie(1, "Super Mario", "", 8.0, null, null, null, "Super Mario"),
    Movie(2, "Super Mario", "", 8.0, null, null, null, "Super Mario"),
)

class GetMovieByIdUseCaseTest {

    @RelaxedMockK
    private lateinit var movieRepository: MovieRepository
    lateinit var getMovieByIdUseCase: GetMovieByIdUseCase


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getMovieByIdUseCase = GetMovieByIdUseCase(movieRepository)
    }

    @Test
    fun `search for a movie by its id`() = runBlocking {
        // Given
        coEvery {
            movieRepository.getMovieById("2")
        } returns movies[1]

        // When
        getMovieByIdUseCase("2")

        // Then
        coVerify(exactly = 1) { movieRepository.getMovieById("2") }
    }
}