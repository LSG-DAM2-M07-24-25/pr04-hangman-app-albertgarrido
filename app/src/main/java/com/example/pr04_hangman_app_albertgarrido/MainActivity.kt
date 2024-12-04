package com.example.pr04_hangman_app_albertgarrido

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pr04_hangman_app_albertgarrido.ui.LaunchScreen.LaunchScreen
import com.example.pr04_hangman_app_albertgarrido.ui.theme.Pr04hangmanappalbertgarridoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pr04hangmanappalbertgarridoTheme {
                var showLaunchScreen by remember { mutableStateOf(true) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (showLaunchScreen) {
                        LaunchScreen(
                            onNavigateToMenu = {
                                showLaunchScreen = false
                            }
                        )
                    } else {
                        Text(
                            text = "MENU DEL JUEGO",
                            modifier = Modifier.padding(innerPadding)
                        )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Pr04hangmanappalbertgarridoTheme {
    }
}}
