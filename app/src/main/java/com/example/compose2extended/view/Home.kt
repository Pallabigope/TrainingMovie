package com.example.compose2extended.view

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose2extended.data.model.MovieResult
import com.example.compose2extended.viewmodel.MovieViewModel

//import com.example.compose2extended.repository.ProductRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(movieView: MovieViewModel= hiltViewModel()) {


    LaunchedEffect(Unit) {
        movieView.getPopularMovies("5983efa2f1f4a4757c565624ee378be8")
    }

    // Observe and display the movies
    val moviesState by movieView.movies.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Upcoming Movies",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                   // overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                IconButton(onClick = { (context as? Activity)?.finish() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
            },
            actions = {
                IconButton(onClick = {  }) {
                    Icon(imageVector = Icons.Filled.Search,
                        contentDescription = null)
                }
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Localized description"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Adjust padding as needed
          //  contentColor = Color.White, // Adjust content color as needed
         //   backgroundColor = MaterialTheme.colorScheme.primary, // Adjust background color as needed
         //   elevation = 4.dp
        )

    // backgroundColor = MaterialTheme.colorScheme.primary,
           // contentColor = MaterialTheme.colorScheme.onPrimary,
           // elevation = 4.dp


      MovieLists(moviesState) // Observe and display the movies
    }


}

@Composable
fun MovieLists(moviesState: List<MovieResult>) {
    LazyColumn{
        items(moviesState.size){
            MovieItem(movie = moviesState[it])
        }
    }

}


@Composable
@Preview
fun HomeScreenPreview() {
  HomeScreen()
}

