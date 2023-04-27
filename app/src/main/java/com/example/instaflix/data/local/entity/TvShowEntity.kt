package com.example.instaflix.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show")
data class TvShowEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val backdropPath: String?,
    val firstAirDate: String,
    val name: String,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val voteAverage: Double,
    val voteCount: Int,
    var page: Int,
)
