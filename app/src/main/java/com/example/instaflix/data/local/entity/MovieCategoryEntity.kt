package com.example.instaflix.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_category")
data class MovieCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val movieId: Int,
    val categoryId: Int,
    val page: Int,
)