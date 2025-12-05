package com.example.movieapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.data.Movie
import com.example.movieapp.ui.components.MovieCard
import com.example.movieapp.ui.theme.*

/**
 * A Composable screen that displays a user's favorite movies.
 *
 * This screen shows a grid of `MovieCard` items representing the movies the user has
 * marked as favorites. If the list of favorite movies is empty, it displays a
 * placeholder message prompting the user to add favorites.
 *
 * @param modifier Optional [Modifier] for this composable.
 * @param favoriteMovies A list of [Movie] objects to be displayed. Defaults to an empty list.
 * @param onMovieClick A callback lambda that is invoked when a movie card is clicked,
 *                     providing the `movieId` of the clicked movie.
 */
@Preview(showBackground = true)
@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    favoriteMovies: List<Movie> = emptyList(),
    onMovieClick: (Int) -> Unit = {}
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(DarkBluePurple)
        ) {
            Text(
                text = "My Favorites",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )

            if (favoriteMovies.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "No Favorites",
                            tint = Color.Gray,
                            modifier = Modifier.size(80.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "No favorite movies yet",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            text = "Add movies to your favorites list",
                            fontSize = 12.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
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
}