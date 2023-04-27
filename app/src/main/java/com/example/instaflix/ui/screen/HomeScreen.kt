package com.example.instaflix.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.instaflix.R
import com.example.instaflix.ui.component.LoadState
import com.example.instaflix.ui.component.MovieHeader
import com.example.instaflix.ui.component.MoviePoster
import com.example.instaflix.ui.theme.InstaflixTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@Composable
fun HomeScreen() {

    val moviesViewModel = hiltViewModel<HomeViewModel>()

    val playingNowMovies = moviesViewModel.getPlayingNow().collectAsLazyPagingItems()
    val popularMovies = moviesViewModel.getPopularMovies().collectAsLazyPagingItems()
    val topRatedMovies = moviesViewModel.getTopRatedMovies().collectAsLazyPagingItems()

    val popularTvShow = moviesViewModel.getPopularTvShow().collectAsLazyPagingItems()
    val topRatedTvShow = moviesViewModel.getTopRatedTvShow().collectAsLazyPagingItems()
    val upcomingTvShow = moviesViewModel.getUpcomingTvShow().collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
    ) {
        val pagerState = rememberLazyListState(
            initialFirstVisibleItemIndex = 1,
        )

        LaunchedEffect(Unit) {
            while (true) {
                yield()
                delay(4500)
                pagerState.animateScrollToItem(
                    index = pagerState.firstVisibleItemIndex + 1,
                )
            }
        }
        LazyRow(
            state = pagerState
        ) {
            items(playingNowMovies) {
                it?.let {
                    MovieHeader(modifier = Modifier.weight(1f), movie = it)
                }
            }
        }

        Text(
            text = stringResource(R.string.movie_popular),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        LazyRow {
            items(
                items = popularMovies
            ) { movie ->
                movie?.let {
                    MoviePoster(posrterPath = it.posterPath)
                }
            }

            val loadState = popularMovies.loadState.mediator
            item {
                LoadState(loadState = loadState, popularMovies.itemCount, onMovieRefresh = {
                    popularMovies.refresh()
                })
            }
        }

        Text(
            text = stringResource(R.string.movie_top_rated),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        LazyRow {
            items(items = topRatedMovies) { movie ->
                movie?.let {
                    MoviePoster(posrterPath = it.posterPath)
                }
            }

            val loadState = topRatedMovies.loadState.mediator
            item {
                LoadState(loadState = loadState, topRatedMovies.itemCount, onMovieRefresh = {
                    topRatedMovies.refresh()
                })
            }
        }

        Text(
            text = stringResource(R.string.tv_show_popular),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        LazyRow {
            items(items = popularTvShow) { movie ->
                movie?.let {
                    MoviePoster(posrterPath = it.posterPath)
                }
            }

            val loadState = popularTvShow.loadState.mediator
            item {
                LoadState(loadState = loadState, popularTvShow.itemCount, onMovieRefresh = {
                    popularTvShow.refresh()
                })
            }
        }

        Text(
            text = stringResource(R.string.tv_show_top_rated),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        LazyRow {
            items(items = topRatedTvShow) { movie ->
                movie?.let {
                    MoviePoster(posrterPath = it.posterPath)
                }
            }

            val loadState = topRatedTvShow.loadState.mediator
            item {
                LoadState(loadState = loadState, topRatedTvShow.itemCount, onMovieRefresh = {
                    topRatedTvShow.refresh()
                })
            }
        }

        Text(
            text = stringResource(R.string.tv_show_upcoming),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        LazyRow {
            items(items = upcomingTvShow) { movie ->
                movie?.let {
                    MoviePoster(posrterPath = it.posterPath)
                }
            }

            val loadState = upcomingTvShow.loadState.mediator
            item {
                LoadState(loadState = loadState, upcomingTvShow.itemCount, onMovieRefresh = {
                    upcomingTvShow.refresh()
                })
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPrev() {
    InstaflixTheme {
        HomeScreen()
    }
}