package com.example.instaflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.instaflix.ui.screen.HomeScreen
import com.example.instaflix.ui.theme.InstaflixTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstaflixTheme {
                HomeScreen()
            }
        }
    }
}
