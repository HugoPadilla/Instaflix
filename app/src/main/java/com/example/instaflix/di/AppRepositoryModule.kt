package com.example.instaflix.di

import com.example.instaflix.data.factory.RemoteMediatorFactory
import com.example.instaflix.data.factory.RemoteMediatorFactoryImpl
import com.example.instaflix.data.repository.MovieRepositoryImpl
import com.example.instaflix.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppRepositoryModule {
    @Binds
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindRemoteMediatorFactory(remoteMediatorFactoryImpl: RemoteMediatorFactoryImpl): RemoteMediatorFactory
}