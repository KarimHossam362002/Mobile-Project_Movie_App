package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.data.Movie
import com.example.movieapp.data.User
import com.example.movieapp.ui.components.FloatingBottomNavigationBar
import com.example.movieapp.ui.screens.FavoritesScreen
import com.example.movieapp.ui.screens.HomeScreen
import com.example.movieapp.ui.screens.MovieDetailsScreen
import com.example.movieapp.ui.screens.ProfileScreen
import com.example.movieapp.ui.screens.SearchScreen
import com.example.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                MovieAppMain()
            }
        }
    }
}

@Composable
fun MovieAppMain() {
    val navController = rememberNavController()
    var favoriteMovieIds by remember { mutableStateOf<Set<Int>>(emptySet()) }
    val currentUser = User(username = "Guest", email = "guest@movieapp.com", password = "")

    val movies = listOf(
        Movie(
            movieId = 1,
            title = "The Beekeeper",
            rating = 8.5,
            posterUrl = "assets/beekeeper.jpg",
            releaseDate = "2025"
        ),
        Movie(
            movieId = 2,
            title = "Lilo & Stitch",
            rating = 7.2,
            posterUrl = "assets/lilo.jpg",
            releaseDate = "2025"
        ),
        Movie(
            movieId = 3,
            title = "House of David",
            rating = 7.8,
            posterUrl = "assets/david.jpg",
            releaseDate = "2025"
        ),
        Movie(
            movieId = 4,
            title = "Mickey 17",
            rating = 6.1,
            posterUrl = "assets/mickey.jpg",
            releaseDate = "2025"
        )
    )

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1A1A2E)
    ) {
        Scaffold(
            bottomBar = {
                FloatingBottomNavigationBar(
                    selectedRoute = currentRoute ?: "home",
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF16213E))
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(paddingValues)
            ) {
                composable("home") {
                    HomeScreen(
                        movies = movies,
                        onMovieClick = { movieId ->
                            navController.navigate("details/$movieId")
                        }
                    )
                }
                composable("search") {
                    SearchScreen(
                        allMovies = movies,
                        onMovieClick = { movieId ->
                            navController.navigate("details/$movieId")
                        }
                    )
                }
                composable("favorites") {
                    FavoritesScreen(
                        favoriteMovies = movies.filter { it.movieId in favoriteMovieIds },
                        onMovieClick = { movieId ->
                            navController.navigate("details/$movieId")
                        }
                    )
                }
                composable("profile") {
                    ProfileScreen(
                        currentUser = currentUser
                    )
                }
                composable("details/{movieId}") { backStackEntry ->
                    val movieId = backStackEntry.arguments?.getString("movieId")?.toInt() ?: 0
                    val selectedMovie = movies.find { it.movieId == movieId }
                    if (selectedMovie != null) {
                        MovieDetailsScreen(
                            movie = selectedMovie,
                            onBackClick = { navController.popBackStack() },
                            onAddToFavorites = {
                                favoriteMovieIds = favoriteMovieIds.toMutableSet().apply {
                                    if (movieId in this) {
                                        remove(movieId)
                                    } else {
                                        add(movieId)
                                    }
                                }
                            },
                            //navController = navController,
                            isFavorite = movieId in favoriteMovieIds
                        )
                    }
                }
            }
        }
    }
}