package com.zfml.movievibe.presentation.detail

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.zfml.movievibe.presentation.components.RatingStars
import com.zfml.movievibe.ui.theme.poppinsFontFamily
import com.zfml.movievibe.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
     viewModel: MovieDetailViewModel = hiltViewModel(),
     navigateToMainScreen: () -> Unit
) {
    val detailUiState by viewModel.detailUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Movie Detail",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = Color.Black,
                        contentDescription = "Back",
                        modifier = Modifier
                            .clickable {
                                navigateToMainScreen()
                            }
                    )
                }
            )
        },
        content = {paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if(detailUiState.isLoading){

                }else {
                    DetailMovieContent(
                        imageUrl = detailUiState.backdropUrl,
                        title = detailUiState.title,
                        releasedDate = detailUiState.releaseDate,
                        overView = detailUiState.overview,
                        rating = detailUiState.rating
                    )
                }

            }
        }
    )
}

@Composable
fun DetailMovieContent(
    imageUrl: String,
    title: String,
    releasedDate: String,
    overView: String,
    rating: Double
){
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            contentDescription = null,
            model = "${Constants.BASE_IMAGE_URL}/${imageUrl}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(369.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Text(
            text = title,
            fontSize = 32.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = releasedDate,
            fontSize = 10.sp,
            fontFamily = poppinsFontFamily,
        )
        Text(
            text = overView,
            fontSize = 16.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(Modifier.height(8.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
            ,
            onClick = {},
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                fontSize = 16.sp,
                text = "Add To Watchlist",
                color = Color.White
            )
        }

    }
}

@Composable
fun RatingListItem(
    title: String,
    rating: Double
){
    Column (
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            text = title,
            fontSize = 16.sp,
            fontFamily = poppinsFontFamily
        )

        Text(text = String.format("%.1f", rating))

        RatingStars(rating = rating/2)


    }
}