package com.example.pr04_hangman_app_albertgarrido.ui.navigation

sealed class Routes(val route: String) {
    data object Launch : Routes("launch")
    data object Menu : Routes("menu")
    data object Game : Routes("game/{difficulty}")
    {
        fun createRoute(difficulty: String) = "game/$difficulty"
    }
    data object Result : Routes("result/{isWin}/{attemptsLeft}/{word}/{difficulty}") {
        fun createRoute(isWin: Boolean, attemptsLeft: Int, word: String, difficulty: String) =
            "result/$isWin/$attemptsLeft/$word/$difficulty"
    }

}