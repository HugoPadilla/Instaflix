package com.example.instaflix.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.instaflix.data.local.entity.TvShowCategoryEntity

@Dao
interface TvShowCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieCategoryEntity: List<TvShowCategoryEntity>)

    @Query("Delete From tv_show_category Where categoryId = :category")
    suspend fun clearAllTvShow(category: Int)
}