package com.zfml.movievibe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zfml.movievibe.presentation.detail.MovieDetailScreen
import com.zfml.movievibe.presentation.main.MainScreen
import kotlinx.serialization.Serializable


@Composable
fun SetUpNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,

        startDestination = MainScreen
    )
    {
        composable<MainScreen> {
           MainScreen(
               navigateToDetailScreen = {
                   navController.navigate(DetailMovieScreen(movieId = it))
               }
           )
        }

        composable<DetailMovieScreen> {
            MovieDetailScreen(
                navigateToMainScreen = {
                    navController.popBackStack()
                }
            )
        }

    }
}

@Serializable
object MainScreen

@Serializable
data class DetailMovieScreen(val movieId: Int)