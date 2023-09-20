package com.examplepart.foodpart.ui.screens.food.foodsbymealresult

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examplepart.foodpart.database.food.FoodEntity
import com.examplepart.foodpart.network.common.Result
import com.examplepart.foodpart.network.common.safeApi
import com.examplepart.foodpart.network.food.FoodDetailApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodsByMealResultViewModel @Inject constructor(
    private val foodDetailApi: FoodDetailApi,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val mealId: String = savedStateHandle.get<String>("mealId").orEmpty()

    private val _foodsList = MutableStateFlow<List<FoodEntity>>(emptyList())
    val foodsList = _foodsList.asStateFlow()

    private val _foodsResult = MutableStateFlow<Result>(Result.Idle)
    val foodsResult = _foodsResult.asSharedFlow()


    init {
        fetchFoodsByMeal()
    }

    fun fetchFoodsByMeal() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = { foodDetailApi.getFoodsByMeal(mealId) },
                onDataReady = { response ->
                    val result = response.similarFoods.map {
                        it.toFoodEntity()
                    }
                    _foodsList.value = result
                }

            ).collect(_foodsResult)
        }
    }

}
