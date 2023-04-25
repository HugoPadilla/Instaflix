package com.example.instaflix.ui.screen

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.instaflix.data.local.entity.MovieEntity
import com.example.instaflix.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
) : ViewModel() {

    fun getPopularMovies(): Flow<PagingData<MovieEntity>> = getPopularMoviesUseCase()

}