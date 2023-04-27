package com.example.instaflix.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.instaflix.data.local.entity.TvShowEntity

@Dao
interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<TvShowEntity>)

    @Query("Select * From tv_show INNER JOIN movie_category ON movie_category.movieId = tv_show.id WHERE categoryId = :category Order By page")
    fun getTvShow(category: Int): PagingSource<Int, TvShowEntity>

}