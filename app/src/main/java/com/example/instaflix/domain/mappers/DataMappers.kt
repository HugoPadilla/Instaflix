package com.example.instaflix.domain.mappers

import com.example.instaflix.data.local.entity.MovieEntity
import com.example.instaflix.domain.models.Movie

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        ogTitle = ogTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        title = title,
    )
}

