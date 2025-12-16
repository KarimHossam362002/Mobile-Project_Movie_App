package com.example.movieapp.ui.navigation

import MovieFB
import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.movieapp.ui.screens.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.movieapp.data.Movie
import com.example.movieapp.data.User
import com.example.movieapp.ui.viewmodel.HomeViewModel
import com.example.movieapp.ui.viewmodel.SessionViewModel
import kotlinx.coroutines.delay

object AppRoutes {
    const val WELCOME = "welcome_route"
    const val LOGIN = "login_route"
    const val REGISTER = "register_route"
    const val HOME = "home_route"
    const val LOADING = "loading_route"
    const val MOVIE_DETAILS = "movie_details_route"

}

@Composable
fun LoadingWrapperScreen(
    targetRoute: String,
    onLoadingComplete: (String) -> Unit
) {

    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(500) // 1 second loading
        isLoading = false
        onLoadingComplete(targetRoute)
    }

    if (isLoading) {
        LoadingScreen()
    }
}

@Composable
fun AppNavigation(sessionViewModel: SessionViewModel = viewModel()) {
    val isLoggedIn by sessionViewModel.isLoggedIn.collectAsState()
    val navController = rememberNavController()
    var pendingRoute by remember { mutableStateOf<String?>(null) }

    // Helper function to navigate with loading
    fun navigateWithLoading(route: String) {
        pendingRoute = route
        navController.navigate(AppRoutes.LOADING)
    }

    NavHost(
        navController = navController,
        startDestination =if (isLoggedIn) AppRoutes.HOME else AppRoutes.WELCOME
    ) {
        composable(AppRoutes.WELCOME) {
            WelcomeScreen(
                onNavigateToLogin = {
                    navigateWithLoading(AppRoutes.LOGIN)
                }
            )
        }

        composable(AppRoutes.LOGIN) {
            LoginScreen(
                onNavigateToRegister = {
                    navigateWithLoading(AppRoutes.REGISTER)
                },
                onNavigateToHome = {
                    navigateWithLoading(AppRoutes.HOME)
                }
            )
        }

        composable(AppRoutes.REGISTER) {
            RegisterPage(
                onNavigateToLogin = {
                    navigateWithLoading(AppRoutes.LOGIN)
                }
            )
        }

        composable(AppRoutes.HOME) {
            HomeScreen(
                onMovieClick = { movieId ->
                    navController.navigate("${AppRoutes.MOVIE_DETAILS}/$movieId")
                }
            )
        }

        composable(
            route = "${AppRoutes.MOVIE_DETAILS}/{movieId}",
            arguments = listOf(
                navArgument("movieId") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val movieId = backStackEntry.arguments?.getString("movieId") ?: return@composable
            val homeViewModel: HomeViewModel = viewModel()

            val movie = homeViewModel.movies.value.firstOrNull { it.id == movieId }
            Log.d("MovieCard", "Movie clicked: ${movie?.title}")
            if (movie != null) {
                MovieDetailsScreen(
                    movie = movie,
                    onBackClick = { navController.popBackStack() },
                    onAddToFavorites = {
                        homeViewModel.toggleFavorite(movie)
                    },
                    isFavorite = homeViewModel.favorites.value.contains(movie)
                )
            } else {
                Text("Movie not found")
            }
        }


        composable(AppRoutes.LOADING) {
            pendingRoute?.let { route ->
                LoadingWrapperScreen(
                    targetRoute = route,
                    onLoadingComplete = { target ->
                        navController.navigate(target) {
                            popUpTo(AppRoutes.LOADING) {
                                inclusive = true
                            }
                        }
                        pendingRoute = null
                    }
                )
            }
        }
    }
}

// Note: Make sure you have a HomeContentScreen or HomeScreen composable
// in your com.example.movieapp.ui.screens package
// If you don't have one yet, create it or replace with your actual home screen