package com.example.movieapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.data.Movie
import com.example.movieapp.ui.components.MovieCard
import com.example.movieapp.ui.theme.DarkBluePurple

@Composable
fun FavoritesContent(
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit
) {
    var favoriteMovies by remember { mutableStateOf(listOf<Movie>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBluePurple)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                ,contentAlignment = Alignment.Center
        ) {
            Text(
                text = "My Favorites",
                fontSize = 28.sp,
                color = Color.White
            )
        }

        if (favoriteMovies.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No favorite movies yet",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favoriteMovies) { movie ->
                    MovieCard(
                        movie = movie,
                        onClick = { onMovieClick(movie.movieId) }
                    )
                }
            }
        }
    }
}
