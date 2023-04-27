package com.example.instaflix.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.instaflix.data.local.entity.MovieCategoryEntity

@Dao
interface MovieCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieCategoryEntity: List<MovieCategoryEntity>)

    @Query("Delete From movie_category Where categoryId = :category")
    suspend fun clearAllMovies(category: Int)
}