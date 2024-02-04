package com.example.compose2extended.data.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val base_url="https://api.themoviedb.org/3/"
interface movieInterface {

    @GET("movie/upcoming")
    fun getMovies(@Query("api_key") apiKey: String): Call<MovieMain>
}

object movieService{
    val movieInstance: movieInterface

    init {
        val retro=
            Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build()
        movieInstance =retro.create(movieInterface::class.java)
    }
}