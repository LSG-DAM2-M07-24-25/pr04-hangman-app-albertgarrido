package com.example.pr04_hangman_app_albertgarrido.ui.result

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pr04_hangman_app_albertgarrido.ui.navigation.Routes

@Composable
fun ResultScreen(
    navController: NavController,
    isWin: Boolean,
    attemptsLeft: Int,
    word: String,
    viewModel: ResultScreenViewModel = viewModel()
) {
    // Configura los datos en el ViewModel
    LaunchedEffect(Unit) {
        viewModel.setResult(isWin, attemptsLeft, word)
    }

    val resultMessage by viewModel.resultMessage.observeAsState("")
    val dynamicMessage by viewModel.dynamicMessage.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = resultMessage, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = dynamicMessage,
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate(Routes.Game.route) {
                    popUpTo(Routes.Result.route) { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            Text("Play Again")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate(Routes.Menu.route) {
                    popUpTo(Routes.Result.route) { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            Text("Menu")
        }
    }
}
