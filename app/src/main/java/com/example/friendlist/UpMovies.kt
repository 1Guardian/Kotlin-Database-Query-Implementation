package com.example.friendlist

data class UpMovies(
    val results: List<UpMovieResult>
)

data class UpMovieResult(
    val overview: String,
    val poster_path: String,
    val title: String,
    val vote_average: Double
)