package com.example.instaflix.ui.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instaflix.ui.screen.home.HomeViewModel
import com.example.instaflix.ui.screen.movie_details.DetailsViewModel
import com.example.instaflix.ui.screen.movie_details.MovieDetailsScreen

@Composable
fun MainNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(moviesViewModel = viewModel, onNavigationToDetails = {
                navController.navigate("details/$it")
            })
        }

        composable(
            "details/{movieId}"
        ) {
            val viewModel = hiltViewModel<DetailsViewModel>()
            MovieDetailsScreen(
                onBackButton = {
                    navController.navigateUp()
                }
            )
        }
    }

}