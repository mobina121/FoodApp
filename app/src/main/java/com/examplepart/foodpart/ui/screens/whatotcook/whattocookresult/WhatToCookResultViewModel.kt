package com.examplepart.foodpart.ui.screens.whatotcook.whattocookresult

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examplepart.foodpart.core.Difficulty
import com.examplepart.foodpart.database.food.FoodEntity
import com.examplepart.foodpart.network.common.Result
import com.examplepart.foodpart.network.common.safeApi
import com.examplepart.foodpart.network.whattocook.WhatToCookApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WhatToCookResultViewModel @Inject constructor(
    private val whatToCookApi: WhatToCookApi,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val ingredients: String = savedStateHandle.get<String>("ingredients").orEmpty()
    val timeLimit: Int = savedStateHandle.get<String>("timeLimit")?.toInt() ?: 0
    val difficulty: String? = savedStateHandle.get<String>("difficulty")


    private val _foodsList = MutableStateFlow<List<FoodEntity>>(emptyList())
    val foodsList = _foodsList.asStateFlow()

    private val _foodsResult = MutableStateFlow<Result>(Result.Idle)
    val foodsResult = _foodsResult.asSharedFlow()

    init {
        findWhatToCook()
    }


    private fun findWhatToCook() {
        viewModelScope.launch(Dispatchers.IO) {
            val ingredientList = ingredients.split(",").map { it.trim() }
            val difficultyId = when (difficulty) {
                Difficulty.EASY.title -> 1
                Difficulty.NORMAL.title -> 2
                Difficulty.DIFFICULT.title -> 3

                else -> null
            }

            safeApi(
                call = {
                    whatToCookApi.whatToCook(
                        ingredients = ingredientList.joinToString(","),
                        timeLimit = timeLimit,
                        difficulty = difficultyId
                    )
                },
                onDataReady = { response ->

                    val result = response.data
                    val foods = result.map {
                        it.toFoodEntity()
                    }
                    _foodsList.value = foods
                }
            ).collect(_foodsResult)
        }
    }

}
