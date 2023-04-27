package com.example.instaflix.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.instaflix.domain.models.Movie
import com.example.instaflix.ui.theme.InstaflixTheme
import com.example.instaflix.utils.Constants

@Composable
fun MovieBanner(modifier: Modifier = Modifier, movie: Movie) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {

        val painter = rememberAsyncImagePainter(
            model = Constants.BASE_IMAGE_URL + "w780" + movie.backdropPath,
        )

        val screenWidthDp = LocalConfiguration.current.screenWidthDp

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .width(screenWidthDp.dp)
                .aspectRatio(1.78f / 1f),
            contentScale = ContentScale.FillBounds,
        )

        val brush =
            Brush.verticalGradient(listOf(Color.Transparent, Color.Black))
        Canvas(
            modifier = Modifier
                .width(screenWidthDp.dp)
                .aspectRatio(1.78f / 1f)
        ) {
            drawRect(brush)
        }

        Column(
            modifier = Modifier
                .width(screenWidthDp.dp)
                .padding(32.dp, 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Sharp.PlayArrow,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MovieBannerPrev() {
    InstaflixTheme {
        Scaffold {
            MovieBanner(
                modifier = Modifier.padding(it),
                movie = Movie(1, "", "", 0.0, "", "", "", "")
            )
        }
    }
}