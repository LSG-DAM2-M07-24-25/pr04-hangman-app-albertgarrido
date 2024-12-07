package com.example.pr04_hangman_app_albertgarrido.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuScreenViewModel : ViewModel() {

    private val _selectedDifficulty = MutableLiveData("Difficulty")
    val selectedDifficulty: LiveData<String> = _selectedDifficulty

    fun setSelectedDifficulty(difficulty: String) {
        _selectedDifficulty.value = difficulty
    }

    fun onPlayButtonClicked(): String? {
        return if (_selectedDifficulty.value != "Difficulty") {
            _selectedDifficulty.value
        } else {
            null
        }
    }

    fun onHelpButtonClicked() {
        // Implementar navegación o lógica para la pantalla de ayuda si es necesario
    }
}
