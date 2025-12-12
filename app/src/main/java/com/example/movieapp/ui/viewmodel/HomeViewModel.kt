package com.example.movieapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.firebase.MovieRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.movieapp.data.firebase.Movie

class HomeViewModel : ViewModel() {

    private val repository = MovieRepository()

    var movies by mutableStateOf<List<Movie>>(emptyList())
        private set

    var currentPage by mutableStateOf(1)
    private val pageSize = 10

    val pagedMovies: List<Movie>
        get() {
            val start = (currentPage - 1) * pageSize
            val end = (start + pageSize).coerceAtMost(movies.size)
            return if (start < end) movies.subList(start, end) else emptyList()
        }

    fun loadMovies() {
        repository.getMovies { list ->
            movies = list.sortedBy { it.id }
        }
    }

    fun nextPage() {
        if (currentPage * pageSize < movies.size) currentPage++
    }

    fun prevPage() {
        if (currentPage > 1) currentPage--
    }

    val totalPages: Int
        get() = (movies.size + pageSize - 1) / pageSize
}
