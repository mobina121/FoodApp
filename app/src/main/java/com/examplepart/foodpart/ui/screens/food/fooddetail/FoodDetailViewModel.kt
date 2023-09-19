package com.examplepart.foodpart.ui.screens.food.fooddetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examplepart.foodpart.database.food.DifficultyEntity
import com.examplepart.foodpart.database.food.FoodEntity
import com.examplepart.foodpart.database.food.MealEntity
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
class FoodDetailViewModel @Inject constructor(
    private val foodDetailApi: FoodDetailApi,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val foodId: String = savedStateHandle.get<String>("id").orEmpty()

    private val _foodDetail = MutableStateFlow<FoodEntity?>(null)
    val foodDetail = _foodDetail.asStateFlow()

    private val _meal = MutableStateFlow<List<MealEntity>>(emptyList())
    val meal = _meal.asStateFlow()

    private val _difficulty = MutableStateFlow<DifficultyEntity?>(null)
    val difficulty = _difficulty.asStateFlow()

    private val _foodDetailResult = MutableStateFlow<Result>(Result.Idle)
    val foodDetailResult = _foodDetailResult.asSharedFlow()

    init {
        fetchFoodDetail()
    }

    private fun fetchFoodDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    foodDetailApi.getFoodDetail(foodId)
                },
                onDataReady = { response ->
                    _foodDetail.value = response.foodInfo.toFoodEntity()

                    val meals = response.additionalInfo.meals.map {
                        it.toMealEntity()
                    }
                    _meal.value = meals

                    _difficulty.value = response.additionalInfo.difficulty.toDifficultyEntity()
                }
            ).collect(_foodDetailResult)
        }
    }
}
