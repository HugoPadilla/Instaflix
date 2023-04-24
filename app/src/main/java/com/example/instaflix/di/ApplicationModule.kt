package com.example.instaflix.di

import com.example.instaflix.data.remote.HeaderInterceptor
import com.example.instaflix.data.remote.MovieApiService
import com.example.instaflix.data.remote.TVShowApiService
import com.example.instaflix.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

}
