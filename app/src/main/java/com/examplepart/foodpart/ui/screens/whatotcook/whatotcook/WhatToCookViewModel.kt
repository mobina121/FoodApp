package com.examplepart.foodpart.ui.screens.whatotcook.whatotcook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examplepart.foodpart.network.common.Result
import com.examplepart.foodpart.network.common.safeApi
import com.examplepart.foodpart.network.whattocook.FoodResponse
import com.examplepart.foodpart.network.whattocook.WhatToCookApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WhatToCookViewModel @Inject constructor(
    private val whatToCookApi: WhatToCookApi
) : ViewModel() {

    private val _foodsList = MutableStateFlow<List<FoodResponse>>(emptyList())
    val foodsList: StateFlow<List<FoodResponse>> = _foodsList.asStateFlow()


    private val _ingredientsValidationState = MutableStateFlow<String?>(null)
    val ingredientsValidationState: StateFlow<String?> = _ingredientsValidationState

    private val _timeLimitValidationState = MutableStateFlow<Int?>(null)
    val timeLimitValidationState: StateFlow<Int?> = _timeLimitValidationState

    private val _whatToCookResult = MutableStateFlow<Result>(Result.Idle)
    val whatToCookResult: SharedFlow<Result> = _whatToCookResult.asSharedFlow()


    fun performValidation(ingredients: String, timeLimit: Int?) {
        val ingredientsError = validateIngredients(ingredients)
        _ingredientsValidationState.value = ingredientsError

        _timeLimitValidationState.value = timeLimit

    }

    fun areAllDataParamsValid(): Boolean {
        return ingredientsValidationState.value == null && timeLimitValidationState.value != null
    }


    fun findWhatToCook(ingredients: String, timeLimit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    whatToCookApi.whatToCook(ingredients = ingredients, timeLimit = timeLimit)
                },
                onDataReady = { response ->
                    _foodsList.value = response.data
                }
            ).collect(_whatToCookResult)
        }
    }


    private fun validateIngredients(ingredientsValidationState: String): String? {
        return if (ingredientsValidationState.length < 3) {
            "ingredientsValidationState must be at least 3 characters long"
        } else {
            null
        }
    }

}