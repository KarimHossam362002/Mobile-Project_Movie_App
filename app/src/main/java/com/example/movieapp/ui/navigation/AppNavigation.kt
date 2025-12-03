package com.example.movieapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.ui.screens.*

object AppRoutes {
    const val WELCOME = "welcome_route"
    const val LOGIN = "login_route"
    const val REGISTER = "register_route"
    const val HOME = "home_route"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.WELCOME
    ) {
        composable(AppRoutes.WELCOME) {
            WelcomeScreen(
                onNavigateToLogin = {
                    navController.navigate(AppRoutes.LOGIN)
                }
            )
        }

        composable(AppRoutes.LOGIN) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(AppRoutes.REGISTER)
                },
                onNavigateToHome = {  // ← ضيف ده
                    navController.navigate(AppRoutes.HOME) {
                        // عشان لما يرجع back ميرجعش لـ Login
                        popUpTo(AppRoutes.WELCOME) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoutes.REGISTER) {
            RegisterPage(
                onNavigateToLogin = {
                    navController.navigate(AppRoutes.LOGIN)
                }
            )
        }

        // ← ضيف الـ Home Screen
        composable(AppRoutes.HOME) {
            HomeScreen(
                movies = emptyList(), // هنا حط الـ movies list بتاعك
                onMovieClick = { movieId ->
                    // لو عايز تروح لـ details screen
                    // navController.navigate("movie_details/$movieId")
                }
            )
        }
    }
}