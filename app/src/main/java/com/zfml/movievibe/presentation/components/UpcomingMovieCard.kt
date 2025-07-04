import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.zfml.movievibe.domain.model.Movie
import com.zfml.movievibe.ui.theme.poppinsFontFamily
import com.zfml.movievibe.util.Constants

@Composable
fun UpcomingMovieCard(
    movie: Movie,
    onClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(149.dp)
            .padding(8.dp)
            .clickable {
                onClicked()
            }
        ,
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