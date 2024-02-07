package com.example.compose2extended.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose2extended.data.model.movieService
import com.example.compose2extended.data.model.MovieMain
import com.example.compose2extended.data.model.MovieResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieService: movieService
): ViewModel() {
    private val _movies= MutableStateFlow<List<MovieResult>>(emptyList())  //This creates a private mutable state flow (_movies) initialized with an empty list of MovieResult. This flow will be used to hold the state of the movie data.
    var movies=_movies.asStateFlow()  //This creates a public read-only state flow (movies) by calling asStateFlow() on the mutable state flow _movies. This is the property that external components can observe for changes in the movie data.
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    fun getPopularMovies(apiKey:String){
        viewModelScope.launch {
            try {
                _isLoading.value=true
                val movieVar= movieService.movieInstance.getMovies(apiKey)
                Log.d("Hello", "API Request URL: ${movieVar.request().url()}")
                movieVar.enqueue(object : Callback<MovieMain> {
                    override fun onResponse(call: Call<MovieMain>, response: Response<MovieMain?>) {
                        if (response.isSuccessful) {
                            val movies = response.body()?.results ?: emptyList()
                            Log.d("Hello", "API Response: $response")
                            Log.d("Hello", "Movies from API: $movies")
                            Log.d("Hello", "Movies updated: ${_movies.value.size}")
                            _movies.value = movies
                        }
                    }



                    override fun onFailure(call: Call<MovieMain?>, t: Throwable) {
                        Log.d("Hello","Error in fetching ${t.localizedMessage}")

                    }
                })


            }
            catch (e: Exception){
                Log.d("Error","Exception ${e.localizedMessage}")

            }
        }

    }




}

