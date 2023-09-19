package com.examplepart.foodpart.ui.screens.whatotcook.whatotcook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examplepart.foodpart.core.Difficulty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WhatToCookViewModel @Inject constructor(
) : ViewModel() {
    private val _ingredients = MutableStateFlow("")
    val ingredients: StateFlow<String> = _ingredients.asStateFlow()

    private val _timeLimit = MutableStateFlow("")
    val timeLimit: StateFlow<String> = _timeLimit.asStateFlow()

    private val _difficultyTitle = MutableStateFlow(Difficulty.NO_MATTER)
    val difficultyTitle: StateFlow<Difficulty> = _difficultyTitle.asStateFlow()


    private val _ingredientsValidationState = MutableStateFlow<String?>(null)
    val ingredientsValidationState: StateFlow<String?> = _ingredientsValidationState

    private val _timeLimitValidationState = MutableStateFlow<String?>(null)
    private val timeLimitValidationState: StateFlow<String?> = _timeLimitValidationState


    fun updateIngredients(ingredients: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _ingredients.emit(ingredients)
        }
    }

    fun updateTimeLimit(timeLimit: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _timeLimit.emit(timeLimit)
        }
    }

    fun updateDifficulty(difficultyTitle: Difficulty) {
        viewModelScope.launch(Dispatchers.IO) {
            _difficultyTitle.emit(difficultyTitle)
        }
    }


    fun performValidation() {
        val ingredientsError = validateIngredients(_ingredients.value)
        val timeLimitError = validateTimeLimit(_timeLimit.value)
        _ingredientsValidationState.value = ingredientsError
        _timeLimitValidationState.value = timeLimitError

    }

    fun areAllDataParamsValid(): Boolean {
        return ingredientsValidationState.value == null && timeLimitValidationState.value == null
    }


    private fun validateIngredients(text: String): String? {
        return if (text.length < 3) {
            "text must be at least 3 characters long"
        } else {
            null
        }
    }

    private fun validateTimeLimit(text: String): String? {
        return if (text.isEmpty()) {
            "text cant be null"
        } else {
            null
        }
    }
}