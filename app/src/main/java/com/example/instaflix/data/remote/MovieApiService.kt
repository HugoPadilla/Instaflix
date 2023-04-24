package com.example.instaflix.data.remote

import com.example.instaflix.data.remote.dto.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    /**
     * Get a list of movies in theatres.
     * This is a release type query that looks for all movies that have a release type of 2 or 3
     * within the specified date range.
     */
    @GET("/movie/now_playing")
    suspend fun getNowPlaying(
        @Query("language") language: String = "es",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

    /**
     * Get a list of the current popular movies on TMDB. This list updates daily.
     */
    @GET("/movie/popular")
    suspend fun getPopular(
        @Query("language") language: String = "es",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

    /**
     * Get the top rated movies on TMDB.
     */
    @GET("/movie/top_rated")
    suspend fun getTopRated(
        @Query("language") language: String = "es",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

    /**
     * Get a list of upcoming movies in theatres.
     * This is a release type query that looks for all movies that have a release
     * type of 2 or 3 within the specified date range.
     */
    @GET("/movie/upcoming")
    suspend fun getUpcoming(
        @Query("language") language: String = "es",
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

}