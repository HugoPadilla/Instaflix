package com.example.instaflix.ui.screen.movie_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instaflix.ui.component.MovieBanner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(viewModel: DetailsViewModel = hiltViewModel(), onBackButton:() -> Unit = {}) {

    val uiState = viewModel.uiState.collectAsState()

    uiState.value.movie?.let { movie ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = movie.title) },
                    navigationIcon = {
                        IconButton(onClick = { onBackButton() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier.padding(it).padding(16.dp)
            ) {
                MovieBanner(movie = movie)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = movie.title,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    softWrap = true,
                    style = MaterialTheme.typography.displaySmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = movie.releaseDate ?: "", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = movie.overview, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}



@Preview
@Composable
fun MovieDetailsScreenPrev() {
    MaterialTheme {
        MovieDetailsScreen()
    }
}