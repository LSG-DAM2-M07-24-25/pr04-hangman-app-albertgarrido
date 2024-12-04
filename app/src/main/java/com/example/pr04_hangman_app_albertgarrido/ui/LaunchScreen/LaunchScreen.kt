package com.example.pr04_hangman_app_albertgarrido.ui.LaunchScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pr04_hangman_app_albertgarrido.R


@Composable
fun LaunchScreen(
    viewModel: LaunchScreenViewModel = viewModel(),
    onNavigateToMenu: () -> Unit
) {
    // Observa el estado del temporizador
    val isTimerFinished by viewModel.isTimerFinished.collectAsState()

    // Navega al menú principal cuando el temporizador termine
    if (isTimerFinished) {
        onNavigateToMenu()
    }

    // UI del Launch Screen
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.loadscreen),
            contentDescription = "App Logo",
            modifier = Modifier.size(200.dp)
        )
    }
}
