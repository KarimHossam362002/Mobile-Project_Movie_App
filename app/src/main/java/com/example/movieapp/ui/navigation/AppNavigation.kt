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

/**
 * Composable function that sets up the navigation graph for the application.
 *
 * This function defines the different screens (routes) of the app and how to navigate
 * between them. It uses a `NavHost` to host the different composable screens.
 * The navigation starts at the [AppRoutes.WELCOME] screen.
 *
 * The defined routes are:
 * - [AppRoutes.WELCOME]: The initial screen that directs users to the login page.
 * - [AppRoutes.LOGIN]: The login screen for existing users. Navigates to the home screen upon successful login.
 * - [AppRoutes.REGISTER]: The registration screen for new users. Navigates to the login screen upon completion.
 * - [AppRoutes.HOME]: The main screen of the app, displayed after a successful login.
 */
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
                onNavigateToHome = {}
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