package com.example.compose2extended.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.compose2extended.R
import com.example.compose2extended.data.model.MovieResult
import com.example.compose2extended.viewmodel.MovieViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieItem(movie: MovieResult,
              movieViewModel: MovieViewModel = hiltViewModel()) {
    var expanded by remember { mutableStateOf(false) }
    var dialogVisible by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            //.clickable { onItemClick(movie) }
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()

        ) {


            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = rememberImagePainter(
                    data = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                    builder = {}),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.original_title,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Release date:${movie.release_date}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Popularity:${movie.popularity}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Overview:  ${movie.overview.take(50)}......(${stringResource(id = R.string.click_here)})",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expanded = !expanded
                        if (expanded) {
                            dialogVisible = true
                        }
                    },
                style = MaterialTheme.typography.bodyMedium


            )
//        if (expanded){
//            Text(
//                text =movie.overview,
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.padding(top=8.dp))
//        }

            if (dialogVisible) {
                OverViewDialog(movie = movie, onClose = { dialogVisible = false })
            }


        }


    }

}


@Composable
fun OverViewDialog(movie: MovieResult, onClose: () -> Unit) {
    var isVisible by remember { mutableStateOf(true) }
    LaunchedEffect(isVisible) {
        if (isVisible) {
            //delay(1000) // Add a delay before starting the animation
            isVisible = false // Trigger the animation
        }
    }


    val animatedScale by animateFloatAsState(
        targetValue = if (isVisible) 0.8f else 1f,
        animationSpec = tween(durationMillis = 1500), label = ""
    )
    Dialog(
        onDismissRequest = {
            onClose()
            isVisible = false
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        // Content of the detailed overview dialog
        Column(
            modifier = Modifier
                .width(800.dp)
                .height(500.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(30.dp)
                .scale(animatedScale)

        ) {
            Text(text = "Detailed Overview", fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = movie.overview, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            // Close button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { onClose() }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                }
            }
        }
    }

}


@Preview
@Composable
fun MovieItemPreview() {
    val sampleMovie = MovieResult(
        adult = false,
        backdrop_path = "/sample_backdrop_path.jpg",
        genre_ids = listOf(1, 2, 3),
        id = 123,
        original_language = "en",
        original_title = "Sample Movie",
        overview = "This is a sample movie overview.",
        popularity = 7.5,
        poster_path = "/sample_poster_path.jpg",
        release_date = "2022-01-01",
        title = "Sample Movie Title",
        video = false,
        vote_average = 8.0,
        vote_count = 100
    )

    MovieItem(movie = sampleMovie)
}

