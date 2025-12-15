package com.example.movieapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.Movie
import com.example.movieapp.data.firebase.MovieRepository
import androidx.compose.runtime.State
import com.example.movieapp.data.firebase.MovieFB

class HomeViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _movies = mutableStateOf<List<MovieFB>>(emptyList())
    val movies: State<List<MovieFB>> = _movies

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        loadMovies()
    }

    fun loadMovies() {
        _isLoading.value = true
        repository.getMovies { movieFBList ->
            try {
                // Convert MovieFB to Movie (Firebase callbacks run on main thread)
                _movies.value = movieFBList.map { movieFB ->
                    MovieFB(
                        id = movieFB.id,
                        title = movieFB.title,
                        storyline = movieFB.storyline,
                        releaseDate = movieFB.releaseDate,
                        posterurl = movieFB.poster ?: movieFB.posterurl,
                        genres = movieFB.genres,
                        actors = movieFB.actors,
                        imdbRating = movieFB.imdbRating,
                        duration = movieFB.duration,
                        ratings = movieFB.ratings,
                        contentRating = movieFB.contentRating,
                        originalTitle = movieFB.originalTitle,
                        year = movieFB.year,
                        poster = movieFB.poster,
                        averageRating = movieFB.averageRating
                    )
                }
            } catch (e: Exception) {
                // If conversion fails, just use empty list
                _movies.value = emptyList()
            }
            _isLoading.value = false
        }
    }
}
