package com.example.movieapp.data.firebase

data class MovieFB(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val year: Int,
    val releaseDate: String,
    val contentRating: String?,
    val storyline: String,
    val genres: List<String>,
    val actors: List<String>,
    val averageRating: Double,
    val imdbRating: Double,
    val duration: String,
    val poster: String?,
    val posterurl: String?,
    val ratings: List<Int>
)

