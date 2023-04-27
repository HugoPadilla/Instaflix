package com.example.instaflix.ui.screen.movie_details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instaflix.domain.usecase.GetMovieByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
) : ViewModel() {

    private val mutableStaFlow = MutableStateFlow(MovieDetailUiState())
    val uiState = mutableStaFlow.asStateFlow()

    init {
        val movieId = savedStateHandle.get<String>("movieId")

        viewModelScope.launch {
            mutableStaFlow.value = mutableStaFlow.value.copy(isLoading = true)

            movieId?.let {
                mutableStaFlow.value = mutableStaFlow.value.copy(
                    isLoading = false,
                    movie = getMovieByIdUseCase(movieId)
                )
            }
        }

        Log.d("DetailsViewModel", "Movie Id is: $movieId")
    }
}