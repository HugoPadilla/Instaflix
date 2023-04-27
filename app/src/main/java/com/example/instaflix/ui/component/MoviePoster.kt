package com.example.instaflix.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.instaflix.ui.theme.InstaflixTheme
import com.example.instaflix.utils.Constants

@Composable
fun MoviePoster(posterPath: String?, onClick: () -> Unit) {
    Row(
        modifier = Modifier.clickable {
            onClick()
        },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (posterPath != null) {
            var isImageLoading by remember { mutableStateOf(false) }

            val painter = rememberAsyncImagePainter(
                model = Constants.BASE_IMAGE_URL + "w185" + posterPath,
            )

            isImageLoading = when (painter.state) {
                is AsyncImagePainter.State.Loading -> true
                else -> false
            }

            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                        .height(278.dp)
                        .width(185.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    painter = painter,
                    contentDescription = "Poster Image",
                    contentScale = ContentScale.FillBounds,
                )

                if (isImageLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(horizontal = 6.dp, vertical = 3.dp),
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MoviePosterPrev() {
    InstaflixTheme {
        MoviePoster(
            posterPath = "",
            onClick = {}
        )
    }
}