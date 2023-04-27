package com.example.instaflix.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.instaflix.data.local.entity.MovieEntity

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("Select * From movies INNER JOIN movie_category ON movie_category.movieId = movies.id WHERE categoryId = :category Order By page")
    fun getMovies(category: Int): PagingSource<Int, MovieEntity>


}