package com.example.friendlist

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UpMovieInterface {
    @GET("/3/movie/upcoming")
    fun getUpMovie(@Query("api_key") key: String): Call<UpMovies>
}