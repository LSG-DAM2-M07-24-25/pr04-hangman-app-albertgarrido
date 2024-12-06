package com.example.pr04_hangman_app_albertgarrido.ui.LaunchScreen
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LaunchScreenViewModel : ViewModel() {

    private val _isTimerFinished = MutableLiveData<Boolean>()
    val isTimerFinished: LiveData<Boolean> = _isTimerFinished


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