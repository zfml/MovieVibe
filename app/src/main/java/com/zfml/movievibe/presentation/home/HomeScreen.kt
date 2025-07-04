package com.zfml.movievibe.presentation.home

import UpcomingMovieCard
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.zfml.movievibe.domain.model.Movie
import com.zfml.movievibe.presentation.components.MovieCard
import com.zfml.movievibe.ui.theme.poppinsFontFamily
import kotlin.math.sign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetailScreen: (Int) -> Unit
) {

    val popularMovies = viewModel.popularMovies.collectAsLazyPagingItems()
    val upComingMovies = viewModel.upComingMovies.collectAsLazyPagingItems()
    val topRatedMovies = viewModel.topRatedMovies.collectAsLazyPagingItems().itemSnapshotList.items.shuffled().take(5)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Movie Vibe",
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFontFamily
                    )
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(bottom = 93.dp)
                    .verticalScroll(rememberScrollState())

            ) {


                TopRatedPagerSection(
                    topRatedMovies
                )

                PopularMovieList(
                    popularMovies,
                    onSeeAllClick = {},
                    onMovieClicked = {
                        navigateToDetailScreen(it)
                    }
                )

                Spacer(Modifier.height(16.dp))


                UpcomingMovieList(
                    movies = upComingMovies,
                    onSeeAllClick = {},
                    onMovieClicked = navigateToDetailScreen
                )

            }
        }
    )
}

@Composable
fun PopularMovieList(
    popularMovies: LazyPagingItems<Movie>,
    onMovieClicked: (Int) -> Unit,
    onSeeAllClick: () -> Unit // Callback for "See All" action
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Popular Movies",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFontFamily
            )
            Text(
                text = "See All",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFontFamily,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable { onSeeAllClick() }
            )
        }

        LazyRow {
            items(popularMovies.itemCount) { index ->
                popularMovies[index]?.let {
                    MovieCard(
                        movie = it,
                        onClicked = {
                            onMovieClicked(it.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun UpcomingMovieList(
    movies: LazyPagingItems<Movie>,
    onSeeAllClick: () -> Unit, // Callback for "See All" action,
    onMovieClicked: (Int) -> Unit,
    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Upcoming Movies",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFontFamily
            )
            Text(
                text = "See All",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFontFamily,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable { onSeeAllClick() }
            )
        }

        LazyRow {
            items(movies.itemCount) { index ->
                movies[index]?.let {
                    UpcomingMovieCard(
                        it,
                        onClicked = {
                            onMovieClicked(it.id)
                        }
                    )
                }
            }
        }
    }
}