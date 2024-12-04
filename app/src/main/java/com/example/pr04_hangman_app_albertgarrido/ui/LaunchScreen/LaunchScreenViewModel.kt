package com.example.pr04_hangman_app_albertgarrido.ui.LaunchScreen
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LaunchScreenViewModel : ViewModel() {

    private val _isTimerFinished = MutableStateFlow(false)
    val isTimerFinished: StateFlow<Boolean> = _isTimerFinished

    init {
        loadGame()
    }

    private fun loadGame() {
        viewModelScope.launch {
            delay(3000)
            _isTimerFinished.value = true
        }
    }

}