package com.example.movieapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.firebase.MovieFB
import com.example.movieapp.data.firebase.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val _movies = MutableStateFlow<List<MovieFB>>(emptyList())
    val movies: StateFlow<List<MovieFB>> = _movies.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadMovies()
    }

    fun loadMovies() {
        _isLoading.value = true
        repository.getMovies { movieFBList ->
            try {
                // استخدم MovieFB مباشرة بدون تحويل، ورتب حسب الـ title
                _movies.value = movieFBList
                    .sortedBy { it.title }
                    .map { movieFB ->
                        // لو عايز تعديل بسيط، زي fallback للـ poster
                        movieFB.copy(
                            posterurl = movieFB.posterurl ?: movieFB.poster ?: ""
                        )
                    }
            } catch (e: Exception) {
                // لو في error في التحويل، استخدم empty list
                _movies.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}