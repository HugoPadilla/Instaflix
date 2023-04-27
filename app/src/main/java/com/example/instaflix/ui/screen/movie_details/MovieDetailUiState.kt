package com.example.instaflix.ui.screen.movie_details

import com.example.instaflix.domain.models.Movie

data class MovieDetailUiState(
    val movie: Movie? = null,
    val isLoading: Boolean = true,
)
