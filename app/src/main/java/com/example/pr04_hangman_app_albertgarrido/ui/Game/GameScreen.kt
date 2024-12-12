package com.example.pr04_hangman_app_albertgarrido.ui.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pr04_hangman_app_albertgarrido.ui.navigation.Routes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pr04_hangman_app_albertgarrido.game.GameScreenViewModel


@Composable
fun GameScreen(
    difficulty: String,
    navController: NavHostController,
    viewModel: GameScreenViewModel = viewModel()
) {
    LaunchedEffect(difficulty) {
        viewModel.initialize(difficulty)
    }

    val revealedWord by viewModel.revealedWord.observeAsState("")
    val attemptsLeft by viewModel.attemptsLeft.observeAsState(6)
    val guessedLetters by viewModel.guessedLetters.observeAsState(emptySet())
    val isGameFinished by viewModel.isGameFinished.observeAsState()

    LaunchedEffect(isGameFinished) {
        isGameFinished?.let { (isWin, score) ->
            navController.navigate(Routes.Result.createRoute(isWin, score, viewModel.getWordToGuess(), difficulty)) {
                popUpTo(Routes.Game.route) { inclusive = true }
            }
            viewModel.resetGameFinishEvent()
        }
    }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Game Status
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Attempts left: $attemptsLeft",
                    style = MaterialTheme.typography.titleLarge,
                    color = when {
                        attemptsLeft > 3 -> MaterialTheme.colorScheme.primary
                        attemptsLeft > 1 -> MaterialTheme.colorScheme.error
                        else -> MaterialTheme.colorScheme.error
                    }
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Word Display
                WordDisplay(
                    revealedWord = revealedWord,
                    modifier = Modifier.padding(vertical = 24.dp)
                )
            }

            // Keyboard
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                items(26) { index ->
                    val letter = ('A' + index)
                    KeyboardButton(
                        letter = letter,
                        isGuessed = guessedLetters.contains(letter),
                        onClick = { viewModel.guessLetter(letter) }
                    )
                }
            }
        }
    }


@Composable
fun KeyboardButton(
    letter: Char,
    isGuessed: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isGuessed) {
            MaterialTheme.colorScheme.surfaceVariant
        } else {
            MaterialTheme.colorScheme.primary
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )

    val contentColor = if (isGuessed) {
        MaterialTheme.colorScheme.onSurfaceVariant
    } else {
        MaterialTheme.colorScheme.onPrimary
    }

    Button(
        onClick = onClick,
        modifier = modifier.size(48.dp),
        enabled = !isGuessed,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text(
            text = letter.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = contentColor
        )
    }
}


@Composable
fun WordDisplay(
    revealedWord: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        revealedWord.forEachIndexed { _, char ->
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = char.toString(),
                        style = MaterialTheme.typography.headlineMedium,
                        color = if (char == '_') {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        },
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    if (char == '_') {
                        Box(
                            modifier = Modifier
                                .width(24.dp)
                                .height(2.dp)
                                .padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

