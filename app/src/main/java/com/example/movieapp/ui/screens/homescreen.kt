package com.example.movieapp.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import com.example.movieapp.data.Movie
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.ui.components.MovieCard
import com.example.movieapp.ui.components.CategoryFilter
import com.example.movieapp.ui.theme.*
import kotlinx.coroutines.delay
@Composable
fun HomeScreen(

movies: List<Movie> = emptyList(),
onMovieClick: (Int) -> Unit = {}
) {


    var selectedTab by remember { mutableStateOf(NavigationTab.HOME) }

    Column(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.weight(1f)) {
            when (selectedTab) {
                NavigationTab.HOME -> HomeContent(movies, onMovieClick)
                NavigationTab.SEARCH -> SearchContent(movies, onMovieClick)
                NavigationTab.FAVORITES -> FavoritesContent(movies, onMovieClick)
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
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit
) {
    val categories = listOf("All", "Adventure", "Comedy", "Fantasy")
    var selectedCategory by remember { mutableStateOf("All") }
    var searchQuery by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    var isSearching by remember { mutableStateOf(false) }
    var debouncedQuery by remember { mutableStateOf("") }

    LaunchedEffect(searchQuery) {
        if (searchQuery.isNotEmpty()) {
            delay(300)
            if (searchQuery == debouncedQuery) {
                debouncedQuery = searchQuery
                isSearching = true
            }
        }
    }

    val filteredMovies = remember(searchQuery, selectedCategory, movies) {
        movies.filter { movie ->
            val matchesSearch = searchQuery.isEmpty() ||
                    movie.title.contains(searchQuery, ignoreCase = true)

            val matchesCategory = selectedCategory == "All" ||
                    (selectedCategory == "Adventure" && movie.title.contains("Adventure", true)) ||
                    (selectedCategory == "Comedy" && movie.title.contains("Comedy", true)) ||
                    (selectedCategory == "Fantasy" && movie.title.contains("Fantasy", true))

            matchesSearch && matchesCategory
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Basecolor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 16.dp)
            ) {
                SearchBar(
                    query = searchQuery,
                    onQueryChange = { newQuery ->
                        searchQuery = newQuery
                        if (newQuery.isEmpty()) {
                            isSearching = false
                            debouncedQuery = ""
                        }
                    },
                    onSearch = {
                        focusManager.clearFocus()
                        if (searchQuery.isNotEmpty()) {
                            debouncedQuery = searchQuery
                            isSearching = true
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                CategoryFilter(
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onCategorySelected = {
                        if (!isSearching) selectedCategory = it
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                if (filteredMovies.isNotEmpty()) {
                    FeaturedMovie(
                        movie = filteredMovies.first(),
                        onClick = { onMovieClick(filteredMovies.first().movieId) }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                SectionTitle("New")
                NewMoviesRow(
                    movies = filteredMovies.take(3),
                    onMovieClick = onMovieClick
                )

                Spacer(modifier = Modifier.height(15.dp))

                if (isSearching && filteredMovies.isNotEmpty()) {
                    SectionTitle("Search Results for '$searchQuery'")

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ) {
                        items(filteredMovies) { movie ->
                            MovieCard(
                                movie = movie,
                                onClick = { onMovieClick(movie.movieId) }
                            )
                        }
                    }
                } else if (!isSearching || filteredMovies.isEmpty()) {
                    SectionTitle("Movies")

                    NewMoviesRow(
                        movies = filteredMovies,
                        onMovieClick = onMovieClick
                    )
                }

                if (isSearching && filteredMovies.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No movies found for '$searchQuery'",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium
                        )
                    }
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
            focusedContainerColor = DarkBluishGray,
            unfocusedContainerColor = DarkBluishGray,
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

@Composable
private fun FeaturedMovie(
    movie: Movie,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        DarkPurple,
                        Turquoise
                    )
                )
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f))
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black)
                    )
                )
                .padding(16.dp)
        ) {
            Text(
                text = movie.title.uppercase(),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "See all",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.clickable { }
        )
    }
}

@Composable
private fun NewMoviesRow(
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(movies) { movie ->
            MovieCard(
                movie = movie,
                onClick = { onMovieClick(movie.movieId) }
            )
        }
    }
}






















