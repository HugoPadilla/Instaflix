package com.example.instaflix.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.LoadStates

@Composable
fun LazyItemScope.LoadState(loadState: LoadStates?, itemCount: Int, onMovieRefresh: () -> Unit = {}) {
    if (loadState?.refresh == LoadState.Loading) {
        Column(
            modifier = Modifier
                .fillParentMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "Refresh Loading"
            )

            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }

    if (loadState?.append == LoadState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }

    if (loadState?.refresh is LoadState.Error || loadState?.append is LoadState.Error) {
        val isPaginatingError =
            (loadState.append is LoadState.Error) || itemCount > 1
        val error = if (loadState.append is LoadState.Error)
            (loadState.append as LoadState.Error).error
        else
            (loadState.refresh as LoadState.Error).error

        val modifier = if (isPaginatingError) {
            Modifier.padding(8.dp)
        } else {
            Modifier.fillParentMaxSize()
        }
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (!isPaginatingError) {
                Icon(
                    modifier = Modifier
                        .size(64.dp),
                    imageVector = Icons.Rounded.Warning, contentDescription = null
                )
            }

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = error.message ?: error.toString(),
                textAlign = TextAlign.Center,
            )

            Button(
                onClick = onMovieRefresh,
                content = {
                    Text(text = "Refresh")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White,
                )
            )
        }
    }
}