package com.examplepart.foodpart.ui.screens.food.fooddetail

import android.util.Log
import androidx.annotation.StringRes
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
import kotlinx.coroutines.flow.StateFlow
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

    private val foodEntity = FoodEntity(
        id = "",
        categoryId = "",
        name = "",
        image = "",
        cookTime = null,
        readyTime = null,
        count = null,
        point = null,
        difficulty = null,
        location = null,
        recipe = null,
        ingredients = null,
        meals = null,
        similarFoods = null
    )


    private val difficultyEntity = DifficultyEntity("", "")

    private val _foodDetail = MutableStateFlow<FoodEntity>(foodEntity)
    val foodDetail: StateFlow<FoodEntity> = _foodDetail.asStateFlow()

    private val _meals = MutableStateFlow<List<MealEntity>>(emptyList())
    val meals: StateFlow<List<MealEntity>> = _meals.asStateFlow()

    private val _difficulty = MutableStateFlow<DifficultyEntity>(difficultyEntity)
    val difficulty: StateFlow<DifficultyEntity?> = _difficulty.asStateFlow()

    private val _similarFoods = MutableStateFlow<List<String>?>(emptyList())
    val similarFoods: StateFlow<List<String>?> = _similarFoods.asStateFlow()

    private val _foodDetailResult = MutableStateFlow<Result>(Result.Idle)
    val foodDetailResult = _foodDetailResult.asSharedFlow()

    private val _tabsData = MutableStateFlow<Map<String, String>?>(null)
    val tabsData: StateFlow<Map<String, String>?> = _tabsData.asStateFlow()


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

                    val tabsData = mutableMapOf<String, String>()
                    if (_foodDetail.value.point != null) {
                        tabsData["اطلاعات بیشتر"] = _foodDetail.value.point.toString()
                    }
                    if (_foodDetail.value.recipe != null) {
                        tabsData["طرز تهیه"] = _foodDetail.value.recipe.toString()
                    }
                    if (_foodDetail.value.ingredients != null) {
                        tabsData["مواد اولیه"] = _foodDetail.value.ingredients.toString()
                    }




                    _tabsData.value = tabsData

                    val meals = response.additionalInfo.meals?.map {
                        it.toMealEntity()
                    }
                    if (meals != null) {
                        _meals.value = meals
                    }

                    _difficulty.value = response.additionalInfo.difficulty.toDifficultyEntity()

                }
            ).collect(_foodDetailResult)
        }
    }
}


