package com.examplepart.foodpart.ui.screens.food.foodsbycategoryresult

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
class FoodsByCategoryResulViewModel @Inject constructor(
    private val foodDetailApi: FoodDetailApi,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val categoryId: String = savedStateHandle.get<String>("categoryId").orEmpty()

    private val _foodsList = MutableStateFlow<List<FoodEntity>>(emptyList())
    val foodsList = _foodsList.asStateFlow()

    private val _foodsResult = MutableStateFlow<Result>(Result.Idle)
    val foodsResult = _foodsResult.asSharedFlow()

    init {
        fetchFoodsByCategory()
    }

    fun fetchFoodsByCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = { foodDetailApi.getFoodsByCategory(categoryId, null, null) },
                onDataReady = { response ->
                    val result = response.foods.map {
                        it.toFoodEntity()
                    }
                    _foodsList.value = result
                }

            ).collect(_foodsResult)
        }
    }

}
