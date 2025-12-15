package com.example.movieapp.ui.viewmodel

import MovieFB
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.Movie
import com.example.movieapp.data.firebase.MovieRepository
import androidx.compose.runtime.State

class HomeViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _movies = mutableStateOf<List<MovieFB>>(emptyList())
    val movies: State<List<MovieFB>> = _movies

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _favorites = mutableStateOf<List<MovieFB>>(emptyList())
    val favorites: State<List<MovieFB>> = _favorites

    init {
        loadMovies()
    }

    fun loadMovies() {
        _isLoading.value = true
        repository.getMovies { movieFBList ->
            _movies.value = movieFBList
            _isLoading.value = false
        }
    }

    fun toggleFavorite(movie: MovieFB) {
        val currentFavs = _favorites.value.toMutableList()
        if (currentFavs.any { it.id == movie.id }) {
            currentFavs.removeAll { it.id == movie.id }
            movie.isFavorite = false
        } else {
            currentFavs.add(movie)
            movie.isFavorite = true
        }
        _favorites.value = currentFavs
    }
}
