package com.example.pr04_hangman_app_albertgarrido.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pr04_hangman_app_albertgarrido.R

@Composable
fun MenuScreen(
    viewModel: MenuScreenViewModel = viewModel(),
    onNavigateToGame: (String) -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Menu(
            Modifier.align(Alignment.Center),
            viewModel,
            onNavigateToGame
        )
    }
}

@Composable
fun Menu(
    modifier: Modifier,
    viewModel: MenuScreenViewModel,
    onNavigateToGame: (String) -> Unit
) {
    val selectedDifficulty by viewModel.selectedDifficulty.observeAsState("Difficulty")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.loadscreen),
            contentDescription = "App Logo",
            modifier = Modifier.size(200.dp)
        )
        DifficultyDropDownMenu(
            selectedDifficulty = selectedDifficulty,
            onDifficultySelected = { difficulty ->
                viewModel.setSelectedDifficulty(difficulty)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                val difficulty = viewModel.onPlayButtonClicked()
                if (difficulty != null) {
                    onNavigateToGame(difficulty)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(text = "Play")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.onHelpButtonClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(text = "Help")
        }
    }
}

@Composable
fun DifficultyDropDownMenu(
    modifier: Modifier = Modifier,
    selectedDifficulty: String,
    onDifficultySelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val difficulties = listOf("Easy", "Medium", "Hard")

    Column(
        modifier = modifier
            .padding(20.dp)
    ) {
        OutlinedTextField(
            value = selectedDifficulty,
            onValueChange = { },
            readOnly = true,
            enabled = false,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
        ) {
            difficulties.forEach { difficulty ->
                DropdownMenuItem(
                    text = { Text(text = difficulty) },
                    onClick = {
                        expanded = false
                        onDifficultySelected(difficulty)
                    }
                )
            }
        }
    }
}