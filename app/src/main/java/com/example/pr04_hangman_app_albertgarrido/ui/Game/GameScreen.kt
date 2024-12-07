package com.example.pr04_hangman_app_albertgarrido.ui.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pr04_hangman_app_albertgarrido.R
import com.example.pr04_hangman_app_albertgarrido.logic.GameScreenViewModel

@Composable
fun GameScreen(difficulty: String, viewModel: GameScreenViewModel = viewModel()) {
    // Llama al método initialize si no se ha inicializado previamente
    LaunchedEffect(difficulty) {
        viewModel.initialize(difficulty)
    }

    val revealedWord by viewModel.revealedWord.observeAsState(initial = "_")
    val attemptsLeft by viewModel.attemptsLeft.observeAsState(initial = 0)
    val gameOver by viewModel.gameOver.observeAsState(initial = false)
    val isWin by viewModel.isWin.observeAsState(initial = false)
    val guessedLetters by viewModel.guessedLetters.observeAsState(initial = emptySet())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Imagen del ahorcado
        Image(
            painter = painterResource(
                id = when (attemptsLeft) {
                    0 -> R.drawable.loadscreen
                    1 -> R.drawable.loadscreen
                    2 -> R.drawable.loadscreen
                    3 -> R.drawable.loadscreen
                    4 -> R.drawable.loadscreen
                    else -> R.drawable.loadscreen
                }
            ),
            contentDescription = "Hangman Image",
            modifier = Modifier.size(200.dp)
        )

        // Palabra a adivinar
        Text(
            text = revealedWord,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        Keyboard(
            onLetterClick = { letter -> viewModel.guessLetter(letter) },
            guessedLetters = guessedLetters
        )

        // Intentos restantes
        Text(
            text = "Intentos restantes: $attemptsLeft",
            modifier = Modifier.padding(16.dp)
        )

        // Mensaje final si el juego terminó
        if (gameOver) {
            Text(
                text = if (isWin) "¡Ganaste!" else "Perdiste... La palabra era ${viewModel.getWordToGuess()}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun Keyboard(onLetterClick: (Char) -> Unit, guessedLetters: Set<Char>) {
    val alphabet = ('A'..'Z').toList()

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        items(alphabet.size) { index ->
            val letter = alphabet[index]
            Button(
                onClick = { onLetterClick(letter) },
                modifier = Modifier.padding(4.dp),
                enabled = !guessedLetters.contains(letter) // Deshabilitar si ya se adivinó
            ) {
                Text(text = letter.toString())
            }
        }
    }
}
