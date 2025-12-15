package com.example.movieapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.Movie
import com.example.movieapp.data.firebase.MovieRepository
import androidx.compose.runtime.State

class HomeViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _movies = mutableStateOf<List<Movie>>(emptyList())
    val movies: State<List<Movie>> = _movies

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        loadMovies()
    }

    fun loadMovies() {
        _isLoading.value = true
        Log.d("HomeViewModel", "Starting to load movies...")
        repository.getMovies { movieFBList ->
            Log.d("HomeViewModel", "Received ${movieFBList.size} movies from Firebase")
            try {
                // Convert MovieFB to Movie (Firebase callbacks run on main thread)
                _movies.value = movieFBList.map { movieFB ->
                    Movie(
                        movieId = movieFB.id,
                        title = movieFB.title,
                        description = movieFB.storyline,
                        releaseDate = movieFB.releaseDate,
                        posterUrl = movieFB.poster ?: movieFB.posterurl,
                        rating = movieFB.averageRating
                    )
                }
                Log.d("HomeViewModel", "Converted to ${_movies.value.size} Movie objects")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error converting movies: ${e.message}", e)
                _movies.value = emptyList()
            }
            _isLoading.value = false
        }
    }
}
