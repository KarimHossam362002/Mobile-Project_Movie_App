package com.example.movieapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.movieapp.ui.screens.*
import androidx.compose.runtime.*
import kotlinx.coroutines.delay

object AppRoutes {
    const val WELCOME = "welcome_route"
    const val LOGIN = "login_route"
    const val REGISTER = "register_route"
    const val HOME = "home_route"
    const val LOADING = "loading_route"
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
fun AppNavigation() {
    val navController = rememberNavController()
    var pendingRoute by remember { mutableStateOf<String?>(null) }

    // Helper function to navigate with loading
    fun navigateWithLoading(route: String) {
        pendingRoute = route
        navController.navigate(AppRoutes.LOADING)
    }

    NavHost(
        navController = navController,
        startDestination = AppRoutes.WELCOME
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
            // Your actual home screen content
            HomeScreen()
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