package com.example.instaflix.data.remote

import com.example.instaflix.data.remote.dto.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowApiService {

    /**
     * Get a list of TV shows that are airing today.
     * This query is purely day based as we do not currently support airing times.
     */
    @GET("/tv/airing_today")
    suspend fun getNowPlaying(
        @Query("language") language: String = "es",
        @Query("page") page: Int = 1
    ): Response<TvShowResponse>

    /**
     * Get a list of shows that are currently on the air.
     * This query looks for any TV show that has an episode with an air date in the next 7 days.
     */
    @GET("/tv/on_the_air")
    suspend fun getUpcoming(
        @Query("language") language: String = "es",
        @Query("page") page: Int = 1
    ): Response<TvShowResponse>

    /**
     * Get a list of the current popular TV shows on TMDB. This list updates daily.
     */
    @GET("/tv/popular")
    suspend fun getPopular(
        @Query("language") language: String = "es",
        @Query("page") page: Int = 1
    ): Response<TvShowResponse>

    /**
     * Get a list of the top rated TV shows on TMDB.
     */
    @GET("/tv/top_rated")
    suspend fun getTopRated(
        @Query("language") language: String = "es",
        @Query("page") page: Int = 1
    ): Response<TvShowResponse>

}