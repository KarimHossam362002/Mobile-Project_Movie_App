package com.example.movieapp.ui.screens
import MovieFB
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import com.example.movieapp.data.Movie
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.ui.theme.*
import com.example.movieapp.ui.viewmodel.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.movieapp.ui.components.MovieCard


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onMovieClick: (Int) -> Unit = {}
) {
    val movies by viewModel.movies
    val isLoading by viewModel.isLoading

    var selectedTab by remember { mutableStateOf(NavigationTab.HOME) }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            when (selectedTab) {
                NavigationTab.HOME -> HomeContent(
                    movies = movies,
                    isLoading = isLoading,
                    viewModel = viewModel,
                    onMovieClick = { movie ->
                        onMovieClick(movie.id?.toInt() ?: 0)
                    }
                )
                NavigationTab.SEARCH -> SearchContent(movies, onMovieClick)
                NavigationTab.FAVORITES -> FavoritesContent(viewModel = viewModel, onMovieClick = onMovieClick)

                NavigationTab.PROFILE -> ProfileContent()
            }
        }


        BottomNavigationBar(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it }
        )
    }
}
@Composable
fun HomeContent(
    movies: List<MovieFB>,
    isLoading: Boolean = false,
    viewModel: HomeViewModel,
    onMovieClick: (MovieFB) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredMovies = if (searchQuery.isEmpty()) {
        movies
    } else {
        movies.filter { it.title.contains(searchQuery, ignoreCase = true) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(12.dp)
    ) {
        Spacer(modifier = Modifier.height(25.dp))

        SearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            onSearch = {  },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Turquoise
                )
            }
        } else if (filteredMovies.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "No movies available",
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredMovies, key = { it.id ?: "" }) { movie ->
                    MovieCard(
                        movie = movie,
                        onClick = {
                            Log.d("MovieCard", "Movie clicked: ${movie.title}")
                            onMovieClick(movie)
                        },
                        onFavoriteClick = { favMovie ->
                            viewModel.toggleFavorite(favMovie)
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = {
            Text(
                text = "Movies, series, shows...",
                color = Color.Gray
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear",
                        tint = Color.White
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearch() }
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .clip(RoundedCornerShape(12.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Darkblue,
            unfocusedContainerColor = Darkblue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedPlaceholderColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Gray,
            cursorColor = Color.White
        ),
        singleLine = true,
        shape = RoundedCornerShape(12.dp)
    )
}
