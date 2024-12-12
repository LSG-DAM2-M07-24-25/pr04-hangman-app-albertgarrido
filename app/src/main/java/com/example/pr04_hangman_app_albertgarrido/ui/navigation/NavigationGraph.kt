package com.example.pr04_hangman_app_albertgarrido.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pr04_hangman_app_albertgarrido.ui.LaunchScreen.LaunchScreen
import com.example.pr04_hangman_app_albertgarrido.ui.game.GameScreen
import com.example.pr04_hangman_app_albertgarrido.ui.menu.MenuScreen
import com.example.pr04_hangman_app_albertgarrido.ui.result.ResultScreen

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
            GameScreen(difficulty = difficulty, navController = navController)
        }

        composable(
            route = Routes.Result.route,
            arguments = listOf(
                navArgument("isWin") { type = NavType.BoolType },
                navArgument("attemptsLeft") { type = NavType.IntType },
                navArgument("word") { type = NavType.StringType },
                navArgument("difficulty") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val isWin = backStackEntry.arguments?.getBoolean("isWin") ?: false
            val attemptsLeft = backStackEntry.arguments?.getInt("attemptsLeft") ?: 0
            val word = backStackEntry.arguments?.getString("word") ?: ""
            val difficulty = backStackEntry.arguments?.getString("difficulty") ?: "Easy"

            ResultScreen(
                navController = navController,
                isWin = isWin,
                attemptsLeft = attemptsLeft,
                word = word,
                difficulty = difficulty
            )
        }
    }
    }
