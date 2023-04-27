package com.example.instaflix.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.instaflix.data.local.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Query("Select * from category Where id = :categoryId")
    fun getCategoryById(categoryId: Int): CategoryEntity
}