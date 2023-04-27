package com.example.instaflix.ui.screen

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.instaflix.data.local.entity.MovieEntity
import com.example.instaflix.data.local.entity.TvShowEntity
import com.example.instaflix.domain.usecase.GetPlayingNowMoviesUseCase
import com.example.instaflix.domain.usecase.GetPopularMoviesUseCase
import com.example.instaflix.domain.usecase.GetPopularTvShowUseCase
import com.example.instaflix.domain.usecase.GetTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPlayingNowMoviesUseCase: GetPlayingNowMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getPopularTvShowUseCase: GetPopularTvShowUseCase,
) : ViewModel() {

    fun getPlayingNow(): Flow<PagingData<MovieEntity>> = getPlayingNowMoviesUseCase()

    fun getPopularMovies(): Flow<PagingData<MovieEntity>> = getPopularMoviesUseCase()

    fun getTopRatedMovies(): Flow<PagingData<MovieEntity>> = getTopRatedMoviesUseCase()

    fun getPopularTvShow(): Flow<PagingData<TvShowEntity>> = getPopularTvShowUseCase()
}