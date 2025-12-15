package com.example.movieapp.data.firebase

data class MovieFB(
    val id: Int = 0,
    val title: String = "",
    val originalTitle: String = "",
    val year: Int = 0,
    val releaseDate: String = "",
    val contentRating: String? = null,
    val storyline: String = "",
    val genres: List<String> = emptyList(),
    val actors: List<String> = emptyList(),
    val averageRating: Double = 0.0,
    val imdbRating: Double = 0.0,
    val duration: String = "",
    val poster: String? = null,
    val posterurl: String? = null,
    val ratings: List<Int> = emptyList()
)

