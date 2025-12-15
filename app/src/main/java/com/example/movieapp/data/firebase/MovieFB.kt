package com.example.movieapp.data.firebase

data class MovieFB(
    var id: Int = 0,
    var title: String = "",
    var originalTitle: String = "",
    var year: Int = 0,
    var releaseDate: String = "",
    var contentRating: String? = null,
    var storyline: String = "",
    var genres: List<String> = emptyList(),
    var actors: List<String> = emptyList(),
    var averageRating: Double = 0.0,
    var imdbRating: Double = 0.0,
    var duration: String = "",
    var poster: String? = null,
    var posterurl: String? = null,
    var ratings: List<Int> = emptyList()
)
