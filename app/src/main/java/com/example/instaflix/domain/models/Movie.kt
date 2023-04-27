package com.example.instaflix.domain.models

data class Movie(
    val id: Int,
    val ogTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val title: String,
)