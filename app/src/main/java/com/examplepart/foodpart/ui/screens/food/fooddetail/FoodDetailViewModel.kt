package com.examplepart.foodpart.ui.screens.food.fooddetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examplepart.foodpart.database.food.FoodEntity
import com.examplepart.foodpart.network.common.Result
import com.examplepart.foodpart.network.common.safeApi
import com.examplepart.foodpart.network.food.FoodDetailApi
import com.examplepart.foodpart.network.food.MealModel
import com.examplepart.foodpart.network.food.SendReportFoodModel
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

    private val _similarFoods = MutableStateFlow<List<FoodEntity>>(emptyList())
    val similarFoods = _similarFoods.asStateFlow()

    private val _similarFoodsResult = MutableStateFlow<Result>(Result.Idle)

    private val _foodDetailResult = MutableStateFlow<Result>(Result.Idle)
    val foodDetailResult = _foodDetailResult.asSharedFlow()

    private val _tabsData = MutableStateFlow<Map<String, String>>(emptyMap())
    val tabsData: StateFlow<Map<String, String>> = _tabsData.asStateFlow()

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

    private val _descriptionReport = MutableStateFlow("")
    val descriptionReport: StateFlow<String> = _descriptionReport.asStateFlow()

    private val _reportAnswer = MutableStateFlow("")
    val reportAnswer: StateFlow<String> = _reportAnswer.asStateFlow()

    private val _reportResult = MutableStateFlow<Result>(Result.Idle)
    val reportResult = _reportResult.asSharedFlow()

    private val _allMeals = MutableStateFlow<List<MealModel>>(emptyList())

    private val _mealId = MutableStateFlow("")

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

                    if (response.additionalInfo.meals != null) {
                        _allMeals.value = response.additionalInfo.meals
                    }


                    val meals = response.additionalInfo.meals?.let { it ->
                        it.map {
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

    fun sendReportFood() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    foodDetailApi.sendReportFood(
                        _foodDetail.value.id,
                        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb2xsZWN0aW9uSWQiOiJfcGJfdXNlcnNfYXV0aF8iLCJleHAiOjE2OTY0MzgxNTksImlkIjoieDk3a2FmaXBpc2R5ZDJxIiwidHlwZSI6ImF1dGhSZWNvcmQifQ.ckL46J-XjVA2RDAPqJ-Z-9RqbSoXlAoo8mYfOTPjc_c",
                        SendReportFoodModel(_descriptionReport.value)
                    )
                },
                onDataReady = { response ->
                    _reportAnswer.value = response

                }
            ).collect(_reportResult)
        }

    }

    fun updateDescriptionReport(description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _descriptionReport.emit(description)
        }
    }

    fun findMealIdByMeal(meal: String): String {
        viewModelScope.launch(Dispatchers.IO) {
            val foundMeal = _allMeals.value.find { it.meal == meal }
            _mealId.value = foundMeal?.mealId ?: "Meal not found"
        }
        return _mealId.value
    }


    fun onTabSelected(index: Int) {
        _currentPage.value = index
    }
}


