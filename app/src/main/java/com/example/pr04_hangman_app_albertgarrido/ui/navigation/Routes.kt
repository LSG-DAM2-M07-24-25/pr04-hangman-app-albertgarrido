package com.example.pr04_hangman_app_albertgarrido.ui.navigation

sealed class Routes(val route: String) {
    object Launch : Routes("launch")
    object Menu : Routes("menu")
    object Game : Routes("game/{difficulty}") {
        fun createRoute(difficulty: String) = "game/$difficulty"
    }
    object Result : Routes("result/{isWin}/{attemptsLeft}/{word}") {
        fun createRoute(isWin: Boolean, attemptsLeft: Int, word: String) =
            "result/$isWin/$attemptsLeft/$word"
    }

}