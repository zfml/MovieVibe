package com.zfml.movievibe.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.zfml.movievibe.presentation.components.Search
import com.zfml.movievibe.presentation.components.SearchMovieCard
import com.zfml.movievibe.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navigateToDetailScreen: (Int) -> Unit
){

    val query by viewModel.query.collectAsState()
    val searchResults = viewModel.searchResults.collectAsLazyPagingItems()

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                        ,
                        text = "Search",
                        fontSize = 16.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                Search(

                    modifier = Modifier.padding(horizontal = 16.dp),
                    searchQuery = query,
                    onSearch = {
                        viewModel.onQueryChanged(it)
                    }
                )


                LazyColumn {
                    items(searchResults.itemCount) { index ->
                        val movie = searchResults[index]
                        if (movie != null) {
                            SearchMovieCard(
                                movie = movie,
                                onClicked = {
                                    navigateToDetailScreen(movie.id)
                                }
                            )
                        }
                    }

                    searchResults.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item {
                                    LoadingScreen()
                                }
                            }
                            loadState.append is LoadState.Loading -> {
                                item {
                                    LoadingScreen()
                                }
                            }
                            loadState.refresh is LoadState.Error -> {
                                val e = searchResults.loadState.refresh as LoadState.Error
                                item {
                                    Text("Error: ${e.error.message}")
                                }
                            }
                        }
                    }
                }
            }

        }
    )
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}