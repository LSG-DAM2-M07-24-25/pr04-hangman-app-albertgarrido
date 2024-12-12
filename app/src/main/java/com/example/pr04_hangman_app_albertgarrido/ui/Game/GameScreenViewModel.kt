package com.example.pr04_hangman_app_albertgarrido.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameScreenViewModel : ViewModel() {

    private val footballPlayersEasy = listOf("Messi", "Ramos", "Xavi", "Gavi", "Kane, Puyol")
    private val footballPlayersMedium = listOf("Neymar", "Mbappe", "Modric", "Haaland", "Pedri, Cubarsi")
    private val footballPlayersHard = listOf("Cristiano Ronaldo", "Robert Lewandowski", "Zlatan Ibrahimovic", "Ferran Torres", "Lamine Yamal")

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

    private val _isGameFinished = MutableLiveData<Triple<Boolean, Int, String>?>()
    val isGameFinished: LiveData<Triple<Boolean, Int, String>?> = _isGameFinished

    // Inicializa la lógica del juego
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

        // Revela los espacios desde el inicio y oculta las letras
        _revealedWord.value = wordToGuess.map { char ->
            if (char.isWhitespace()) " " else "_"
        }.joinToString("")

        _attemptsLeft.value = maxAttempts
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

    fun resetGameFinishEvent() {
        _isGameFinished.value = null
    }

    private fun checkWinCondition() {
        if (_revealedWord.value?.equals(wordToGuess, ignoreCase = true) == true) {
            _isWin.value = true
            _gameOver.value = true
            _isGameFinished.value = Triple(true, _attemptsLeft.value ?: 0, wordToGuess)
        }
    }

    private fun checkLoseCondition() {
        if (_attemptsLeft.value == 0) {
            _gameOver.value = true
            _isGameFinished.value = Triple(false, 0, wordToGuess)
        }
    }



    // Método para obtener la palabra a adivinar
    fun getWordToGuess(): String = wordToGuess
}
