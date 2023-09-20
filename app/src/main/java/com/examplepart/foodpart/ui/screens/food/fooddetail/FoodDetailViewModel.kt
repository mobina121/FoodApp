package com.examplepart.foodpart.ui.screens.food.fooddetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _foodDetail = MutableStateFlow(foodEntity)
    val foodDetail: StateFlow<FoodEntity> = _foodDetail.asStateFlow()

    private val _meals = MutableStateFlow<List<String>>(emptyList())
    val meals: StateFlow<List<String>> = _meals.asStateFlow()

    private val _difficultyLevel = MutableStateFlow("")
    val difficultyLevel: StateFlow<String?> = _difficultyLevel.asStateFlow()

    private val _similarFoodsIds = MutableStateFlow<List<String>?>(emptyList())
    val similarFoodsIds: StateFlow<List<String>?> = _similarFoodsIds.asStateFlow()

    private val _similarFoods = MutableStateFlow<List<FoodEntity>>(emptyList())
    val similarFoods = _similarFoods.asStateFlow()

    private val _foodIds = MutableStateFlow("")
    val foodIds: StateFlow<String> = _foodIds.asStateFlow()

    private val _similarFoodsResult = MutableStateFlow<Result>(Result.Idle)
    val similarFoodsResult = _similarFoodsResult.asSharedFlow()

    private val _foodDetailResult = MutableStateFlow<Result>(Result.Idle)
    val foodDetailResult = _foodDetailResult.asSharedFlow()

    private val _tabsData = MutableStateFlow<Map<String, String>>(emptyMap())
    val tabsData: StateFlow<Map<String, String>> = _tabsData.asStateFlow()

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

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

                    _foodDetail.value.ingredients?.let {
                        tabsData["مواد اولیه"] = it
                    }
                    _foodDetail.value.recipe?.let {
                        tabsData["طرز تهیه"] = it
                    }
                    _foodDetail.value.point?.let {
                        tabsData["اطلاعات بیشتر"] = it
                    }

                    tabsData.let {
                        _tabsData.value = tabsData
                    }

                    _currentPage.value = 0

                    val meals = response.additionalInfo.meals?.let { it ->
                        it.map { it ->
                            it.meal
                        }
                    }

                    if (meals != null) {
                        _meals.value = meals
                    }

                    _similarFoodsIds.value = response.additionalInfo.similarFoods

                    _difficultyLevel.value =
                        if (response.additionalInfo.difficulty.difficultyId == response.foodInfo.difficulty)
                            response.additionalInfo.difficulty.difficultyLevel else ""

                    if (_foodDetailResult.value is Result.Success) {
                        fetchFoodsByIds()
                    }


                }
            ).collect(_foodDetailResult)
        }
    }

    fun fetchFoodsByIds() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = { foodDetailApi.getFoodsByIds(_similarFoodsIds.value.toString()) },
                onDataReady = { response ->
                    val result = response.similarFoods.map {
                        it.toFoodEntity()
                    }
                    _similarFoods.value = result
                }

            ).collect(_similarFoodsResult)
        }
    }


    fun onTabSelected(index: Int) {
        _currentPage.value = index
    }
}


