package com.example.friendlist

data class PopMovies(
    val results: List<PopMoviesResult>
)

data class PopMoviesResult(
    val overview: String,
    val poster_path: String,
    val title: String,
    val vote_average: Double
)