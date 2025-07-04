package com.zfml.movievibe.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zfml.movievibe.R
import com.zfml.movievibe.presentation.home.HomeScreen
import kotlinx.serialization.Serializable

enum class Destination(
    val route: String,
    val label: String,
    val icon: Int,
    val iconActive: Int,
    val contentDescription: String
) {
    HOME(
        route = HomeScreen.toString(),
        label = "Home",
        icon = R.drawable.outline_home_24,
        iconActive = R.drawable.baseline_home_filled_24,
        contentDescription = "Home"
    ),
    SEARCH(
        route = SearchScreen.toString(),
        label = "Search",
        icon = R.drawable.baseline_search_24,
        iconActive = R.drawable.baseline_search_24,
        contentDescription = "Favourite"
    ),
    FAVOURITE(
        route = FavouriteScreen.toString(),
        label = "Favourite",
        icon = R.drawable.baseline_favorite_border_24,
        iconActive = R.drawable.baseline_favorite_24,
        contentDescription = "Favourite"
    ),

}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier,

) {
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.HOME -> HomeScreen(
                    )
                    Destination.FAVOURITE ->  {
                        Text("I am Favourite")
                    }
                    Destination.SEARCH -> {
                        Text("I am Search")
                    }
                }
            }
        }


        composable<HomeScreen> (
            content = {
                HomeScreen()
            }
        )


        composable<SearchScreen> {
            Text("I am a search screen")
        }






    }
}




@Serializable
object HomeScreen

@Serializable
object SearchScreen

@Serializable
object FavouriteScreen
