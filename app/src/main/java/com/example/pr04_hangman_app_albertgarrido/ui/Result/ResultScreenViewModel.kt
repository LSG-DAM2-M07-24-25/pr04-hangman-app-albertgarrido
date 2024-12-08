package com.example.pr04_hangman_app_albertgarrido.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultScreenViewModel : ViewModel() {

    private val _resultMessage = MutableLiveData<String>()
    val resultMessage: LiveData<String> = _resultMessage

    private val _dynamicMessage = MutableLiveData<String>()
    val dynamicMessage: LiveData<String> = _dynamicMessage

    // Configurar los datos iniciales desde los parámetros pasados
    fun setResult(isWin: Boolean, attemptsLeft: Int, word: String) {
        _resultMessage.value = if (isWin) {
            "Congratulations! You succeeded."
        } else {
            "You lost! Better luck next time."
        }
        _dynamicMessage.value = if (isWin) {
            "You finished the game with $attemptsLeft attempts left."
        } else {
            "The word was \"$word\"."
        }

    }
}
