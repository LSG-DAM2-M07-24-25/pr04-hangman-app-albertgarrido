package com.example.pr04_hangman_app_albertgarrido.ui.LaunchScreen
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class LaunchScreenViewModel : ViewModel() {

    private val _isTimerFinished = MutableLiveData<Boolean>()
    val isTimerFinished: LiveData<Boolean> = _isTimerFinished

    private val _progress = MutableLiveData<Float>()
    val progress: LiveData<Float> = _progress

    init {
        loadGame()
    }

    private fun loadGame() {
        viewModelScope.launch {
            val duration = 3000L // Duración total (3 segundos)
            val steps = 50 // Dividimos el tiempo en pasos para una animación fluida
            val stepDuration = duration / steps // Duración de cada paso

            for (i in 0..steps) {
                _progress.value = i / steps.toFloat() // Actualizamos el progreso como fracción
                delay(stepDuration)
            }
            _isTimerFinished.value = true // Marca el temporizador como terminado
        }
    }
}