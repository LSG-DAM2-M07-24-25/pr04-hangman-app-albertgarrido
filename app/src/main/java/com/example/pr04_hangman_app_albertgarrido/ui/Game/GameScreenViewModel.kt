package com.example.pr04_hangman_app_albertgarrido.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameScreenViewModel : ViewModel() {

    private val footballPlayersEasy = listOf("Messi", "Ramos", "Xavi", "Iniesta", "Kane")
    private val footballPlayersMedium = listOf("Neymar", "Mbappe", "Modric", "Haaland", "DeBruyne")
    private val footballPlayersHard = listOf("CristianoRonaldo", "Lewandowski", "Ibrahimovic", "VanDijk", "Fernandes")

    private lateinit var wordToGuess: String
    private var maxAttempts: Int = 0

    private val _attemptsLeft = MutableLiveData<Int>()
    val attemptsLeft: LiveData<Int> = _attemptsLeft

    private val _revealedWord = MutableLiveData<String>()
    val revealedWord: LiveData<String> = _revealedWord

    private val _gameOver = MutableLiveData(false)
    val gameOver: LiveData<Boolean> = _gameOver

    private val _isWin = MutableLiveData(false)
    val isWin: LiveData<Boolean> = _isWin

    private val _guessedLetters = MutableLiveData<Set<Char>>(emptySet())
    val guessedLetters: LiveData<Set<Char>> = _guessedLetters

    // Método para inicializar la lógica del juego
    fun initialize(difficulty: String) {
        wordToGuess = when (difficulty) {
            "Easy" -> footballPlayersEasy.random()
            "Medium" -> footballPlayersMedium.random()
            "Hard" -> footballPlayersHard.random()
            else -> footballPlayersEasy.random()
        }

        maxAttempts = when (difficulty) {
            "Easy" -> 10
            "Medium" -> 7
            "Hard" -> 5
            else -> 10
        }

        // Inicializa LiveData con valores iniciales
        _attemptsLeft.value = maxAttempts
        _revealedWord.value = "_".repeat(wordToGuess.length)
        _gameOver.value = false
        _isWin.value = false
        _guessedLetters.value = emptySet()
    }

    fun guessLetter(letter: Char) {
        if (_gameOver.value == true) return
        if (!letter.isLetter() || (_guessedLetters.value?.contains(letter) == true)) return

        _guessedLetters.value = _guessedLetters.value?.plus(letter)

        if (wordToGuess.contains(letter, ignoreCase = true)) {
            revealLetter(letter)
            checkWinCondition()
        } else {
            _attemptsLeft.value = _attemptsLeft.value?.minus(1)
            checkLoseCondition()
        }
    }

    private fun revealLetter(letter: Char) {
        val updatedWord = _revealedWord.value?.toCharArray()
        wordToGuess.forEachIndexed { index, char ->
            if (char.equals(letter, ignoreCase = true)) {
                updatedWord?.set(index, char)
            }
        }
        _revealedWord.value = updatedWord?.concatToString()
    }

    private fun checkWinCondition() {
        if (_revealedWord.value?.equals(wordToGuess, ignoreCase = true) == true) {
            _isWin.value = true
            _gameOver.value = true
        }
    }

    private fun checkLoseCondition() {
        if (_attemptsLeft.value == 0) {
            _gameOver.value = true
        }
    }

    // Método para obtener la palabra a adivinar
    fun getWordToGuess(): String = wordToGuess
}
