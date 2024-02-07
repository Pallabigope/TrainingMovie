package com.example.compose2extended.network

import com.example.compose2extended.data.model.MovieMain
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

const val base_url="https://api.themoviedb.org/3/"
interface movieInterface {

    @GET("movie/upcoming")
    fun getMovies(@Query("api_key") apiKey: String): Call<MovieMain>
}

@Singleton
class movieService @Inject constructor(retrofit: Retrofit){
    val movieInstance: movieInterface = retrofit.create(movieInterface::class.java)

} 