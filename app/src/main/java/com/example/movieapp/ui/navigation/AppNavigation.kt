package com.example.movieapp.ui.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.movieapp.ui.screens.*
object AppRoutes {
    const val WELCOME = "welcome_route"
    const val LOGIN = "login_route"
    const val REGISTER = "register_route"

    const val LOADING = "loading_route"
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
                
            )
        }
        composable(AppRoutes.REGISTER) {
            RegisterPage(onNavigateToLogin= {
                navController.navigate(AppRoutes.LOGIN)
            })
        }
        composable(AppRoutes.LOADING) {
            LoadingScreen()
        }
    }
}