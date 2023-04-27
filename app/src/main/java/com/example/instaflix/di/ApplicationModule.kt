package com.example.instaflix.di

import android.content.Context
import androidx.room.Room
import com.example.instaflix.data.local.MoviesDatabase
import com.example.instaflix.data.local.dao.MoviesDao
import com.example.instaflix.data.local.dao.RemoteKeysDao
import com.example.instaflix.data.remote.HeaderInterceptor
import com.example.instaflix.data.remote.MovieApiService
import com.example.instaflix.data.remote.TVShowApiService
import com.example.instaflix.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    @Singleton
    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService =
        retrofit.create(MovieApiService::class.java)

    @Singleton
    @Provides
    fun provideTvShowApiService(retrofit: Retrofit): TVShowApiService =
        retrofit.create(TVShowApiService::class.java)

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MoviesDatabase = Room
        .databaseBuilder(context, MoviesDatabase::class.java, "movies_database")
        .build()

    @Singleton
    @Provides
    fun provideMoviesDao(moviesDatabase: MoviesDatabase): MoviesDao = moviesDatabase.getMoviesDao()

    @Singleton
    @Provides
    fun provideRemoteKeysDao(moviesDatabase: MoviesDatabase): RemoteKeysDao =
        moviesDatabase.getRemoteKeysDao()
}
