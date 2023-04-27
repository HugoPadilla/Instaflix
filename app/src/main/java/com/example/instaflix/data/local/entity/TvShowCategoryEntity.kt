package com.example.instaflix.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show_category")
data class TvShowCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val tvShowId: Int,
    val categoryId: Int,
    val page: Int,
)