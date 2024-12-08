package com.example.pr04_hangman_app_albertgarrido.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pr04_hangman_app_albertgarrido.ui.LaunchScreen.LaunchScreen
import com.example.pr04_hangman_app_albertgarrido.ui.menu.MenuScreen
import com.example.pr04_hangman_app_albertgarrido.ui.game.GameScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Launch.route) {
        composable(Routes.Launch.route) {
            LaunchScreen {
                navController.navigate(Routes.Menu.route) {
                    popUpTo(Routes.Launch.route) { inclusive = true }
                }
            }
        }

        composable(Routes.Menu.route) {
            MenuScreen { difficulty ->
                navController.navigate(Routes.Game.createRoute(difficulty))
            }
        }

        composable(Routes.Game.route) { backStackEntry ->
            val difficulty = backStackEntry.arguments?.getString("difficulty") ?: "Easy"
            GameScreen(difficulty)
        }
    }
}
