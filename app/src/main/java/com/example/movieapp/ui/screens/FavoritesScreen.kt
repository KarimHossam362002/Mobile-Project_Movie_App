package com.example.movieapp.ui.screens

import MovieFB
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

import com.example.movieapp.ui.components.MovieCard
import com.example.movieapp.ui.theme.*
import com.example.movieapp.ui.viewmodel.HomeViewModel

@Composable
fun FavoritesContent(
    viewModel: HomeViewModel,
    onMovieClick: (Int) -> Unit
) {
    val favoriteMovies by viewModel.favorites

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
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
                        onClick = {
                            movie.id?.toIntOrNull()?.let { onMovieClick(it) }
                        },
                        onFavoriteClick = { viewModel.toggleFavorite(movie) }
                    )
                }
            }
        }
    }
}
