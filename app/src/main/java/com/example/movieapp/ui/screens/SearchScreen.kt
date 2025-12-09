package com.example.movieapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.data.Movie
import com.example.movieapp.ui.components.MovieCard
import com.example.movieapp.ui.theme.DarkBluePurple

@Composable
fun SearchContent(
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    val searchResults = remember(searchQuery, movies) {
        if (searchQuery.isEmpty()) emptyList()
        else movies.filter { it.title.contains(searchQuery, ignoreCase = true) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBluePurple)
            .padding(16.dp)
    ) {
        SearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it},
            onSearch = { focusManager.clearFocus() },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (searchResults.isEmpty() && searchQuery.isNotEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "لا توجد نتائج",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(searchResults) { movie ->
                    MovieCard(
                        movie = movie,
                        onClick = { onMovieClick(movie.movieId) }
                    )
                }
            }
        }
    }
}
