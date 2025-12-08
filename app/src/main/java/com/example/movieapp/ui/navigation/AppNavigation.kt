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

}



@Composable
fun LoadingWrapperScreen(
    onLoadingComplete: () -> Unit
) {
    // 1. Define the loading state
    var isLoading by remember { mutableStateOf(true) }

    // 2. Start the data loading effect
    LaunchedEffect(Unit) {
        delay(1000)
        isLoading = false
        onLoadingComplete()
    }
    if (isLoading) {
        LoadingScreen()
    }
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
                // When navigating home, we go straight to the Home route
                onNavigateToHome = {
                    navController.navigate(AppRoutes.HOME)
                }
            )
        }
        composable(AppRoutes.REGISTER) {
            RegisterPage(onNavigateToLogin= {
                navController.navigate(AppRoutes.LOGIN)
            })
        }
        composable(AppRoutes.HOME) {
            LoadingWrapperScreen {
                navController.navigate("home_content_route")
            }
        }
        composable("home_content_route") {
            HomeScreen()
        }
//        composable(AppRoutes.LOADING){
//            LoadingScreen()
//        }
    }
}