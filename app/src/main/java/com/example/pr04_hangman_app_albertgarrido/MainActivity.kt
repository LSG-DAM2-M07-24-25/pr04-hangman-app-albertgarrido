package com.example.pr04_hangman_app_albertgarrido

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pr04_hangman_app_albertgarrido.ui.LaunchScreen.LaunchScreen
import com.example.pr04_hangman_app_albertgarrido.ui.game.GameScreen
import com.example.pr04_hangman_app_albertgarrido.ui.menu.MenuScreen
import com.example.pr04_hangman_app_albertgarrido.ui.navigation.NavigationGraph
import com.example.pr04_hangman_app_albertgarrido.ui.theme.Pr04hangmanappalbertgarridoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pr04hangmanappalbertgarridoTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationGraph(navController = navController)
                }
            }}}
}
