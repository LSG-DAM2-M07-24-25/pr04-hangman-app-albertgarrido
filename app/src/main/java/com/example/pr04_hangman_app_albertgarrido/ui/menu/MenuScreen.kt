package com.example.pr04_hangman_app_albertgarrido.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pr04_hangman_app_albertgarrido.R

@Composable
fun MenuScreen(modifier: Modifier = Modifier, viewModel: MenuScreenViewModel = viewModel()
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Menu(Modifier.align(Alignment.Center), viewModel)
    }

}

@Composable
fun Menu(modifier: Modifier, viewModel: MenuScreenViewModel = viewModel()) {


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
        DifficultyDropDownMenu()

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { viewModel.onPlayButtonClicked()},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
        ) {
            Text(text = "Play")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.onHelpButtonClicked()},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
        ) {
            Text(text = "Help")
        }


    }
}
@Composable
fun DifficultyDropDownMenu(modifier: Modifier = Modifier) {
    var selectedText: String by remember { mutableStateOf("Difficulty") }
    var expanded: Boolean by remember { mutableStateOf(false) }

    val difficulties = listOf("Easy", "Medium", "Hard")

    Column(
        modifier = modifier
            .padding(20.dp)
    ) {
        // Campo de texto desplegable
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true } // Abre el menú al hacer clic
                .fillMaxWidth()
        )

        // Menú desplegable
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp, Color.Black, RoundedCornerShape(4.dp)
                )
        ) {
            difficulties.forEach { difficulty ->
                DropdownMenuItem(
                    text = { Text(text = difficulty) },
                    onClick = {
                        expanded = false
                        selectedText = difficulty
                    }
                )
            }
        }
    }
}