package com.example.instaflix.data.mappers

import com.example.instaflix.data.local.entity.MovieEntity
import com.example.instaflix.data.local.entity.TvShowEntity
import com.example.instaflix.data.remote.dto.MovieDto
import com.example.instaflix.data.remote.dto.TvShowDto

fun MovieDto.toEntity(pageInt: Int): MovieEntity {
    return MovieEntity(
        id = id,
        ogTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        title = title,
        page = pageInt
    )
}

fun TvShowDto.toEntity(pageInt: Int): TvShowEntity {
    return TvShowEntity(
        id = id,
        backdropPath = backdropPath,
        firstAirDate = firstAirDate,
        name = name,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        voteAverage = voteAverage,
        voteCount = voteCount,
        page = pageInt
    )
}