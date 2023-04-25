package com.example.instaflix.data.mappers

import com.example.instaflix.data.local.entity.MovieEntity
import com.example.instaflix.data.remote.dto.MovieDto

fun MovieDto.toEntity(pageInt: Int): MovieEntity {
    return MovieEntity(
        id = id,
        ogTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        page = pageInt
    )
}