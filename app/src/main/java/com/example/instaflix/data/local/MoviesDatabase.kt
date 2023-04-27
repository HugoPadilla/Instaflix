package com.example.instaflix.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.instaflix.data.local.dao.CategoryDao
import com.example.instaflix.data.local.dao.MovieCategoryDao
import com.example.instaflix.data.local.dao.MoviesDao
import com.example.instaflix.data.local.dao.RemoteKeysDao
import com.example.instaflix.data.local.dao.TvShowCategoryDao
import com.example.instaflix.data.local.dao.TvShowDao
import com.example.instaflix.data.local.dao.TvShowRemoteKeysDao
import com.example.instaflix.data.local.entity.CategoryEntity
import com.example.instaflix.data.local.entity.MovieCategoryEntity
import com.example.instaflix.data.local.entity.MovieEntity
import com.example.instaflix.data.local.entity.RemoteKeys
import com.example.instaflix.data.local.entity.TvShowCategoryEntity
import com.example.instaflix.data.local.entity.TvShowEntity
import com.example.instaflix.data.local.entity.TvShowRemoteKeys

@Database(
    entities = [
        MovieEntity::class, RemoteKeys::class, CategoryEntity::class, MovieCategoryEntity::class,
        TvShowEntity::class, TvShowCategoryEntity::class, TvShowRemoteKeys::class
    ],
    version = 1,
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getMovieCategoryDao(): MovieCategoryDao
    abstract fun getTvShowDao(): TvShowDao
    abstract fun getTvShowRemoteKeysDao(): TvShowRemoteKeysDao
    abstract fun getTvShowCategoryDao(): TvShowCategoryDao
}