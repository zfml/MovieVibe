package com.zfml.movievibe.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.zfml.movievibe.domain.model.Movie
import com.zfml.movievibe.ui.theme.poppinsFontFamily
import com.zfml.movievibe.util.Constants
import dagger.Provides

@Composable
fun MovieCard(
    movie: Movie
) {
    Card(
        modifier = Modifier
            .width(149.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            // Image with rating overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(196.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxSize(),
                    model = "${Constants.BASE_IMAGE_URL}/${movie.posterUrl}",
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )

                // Star rating overlay (top-left corner)
                Row(
                    modifier = Modifier
                        .padding(6.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                        .align(Alignment.TopStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating Star",
                        tint = Color.White,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = String.format("%.1f", movie.rating),
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            // Title and release date
            Column(modifier = Modifier.padding(horizontal = 4.dp)) {
                Text(
                    text = movie.title,
                    fontSize = 16.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
                Text(
                    text = movie.releaseDate,
                    fontSize = 9.sp,
                    fontFamily = poppinsFontFamily,
                )
            }
        }
    }
}
@Composable
@Preview
fun MovieCardPreview() {
    MovieCard(
        movie = Movie(
            id = 1,
            title = "Movie",
            overview = "adf",
            posterUrl = "",
            backdropUrl ="",
            releaseDate = "23432",
            rating = 3.0
        )
    )
}