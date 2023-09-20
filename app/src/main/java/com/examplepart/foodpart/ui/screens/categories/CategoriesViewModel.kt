package com.examplepart.foodpart.ui.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examplepart.foodpart.database.categories.CategoryDao
import com.examplepart.foodpart.database.categories.CategoryEntity
import com.examplepart.foodpart.database.food.FoodDao
import com.examplepart.foodpart.database.food.FoodEntity
import com.examplepart.foodpart.network.categories.CategoriesApi
import com.examplepart.foodpart.network.common.Result
import com.examplepart.foodpart.network.common.safeApi
import com.examplepart.foodpart.network.food.FoodApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoryDao: CategoryDao,
    private val categoriesApi: CategoriesApi,
    private val foodApi: FoodApi,
    private val foodDao: FoodDao
) : ViewModel() {
    private var _selectedCategoryIndex = MutableStateFlow<Int>(0)
    val selectedCategoryIndex = _selectedCategoryIndex.asStateFlow()

    private val _selectedSubCategoryIndex = MutableStateFlow<Int>(0)
    val selectedSubCategoryIndex = _selectedSubCategoryIndex.asStateFlow()


    private val _categories = MutableStateFlow<List<CategoryEntity>>(emptyList())
    val categories = _categories.asStateFlow()


    private val _subCategories = MutableStateFlow<List<CategoryEntity>>(emptyList())
    val subCategories = _subCategories.asStateFlow()

    private val _categoryResult = MutableStateFlow<Result>(Result.Idle)
    val categoryResult = _categoryResult.asSharedFlow()

    private val _foodResult = MutableStateFlow<Result>(Result.Idle)
    val foodResult = _foodResult.asSharedFlow()

    private val _foods = MutableStateFlow<List<FoodEntity>>(mutableListOf())
    val foods = _foods.asStateFlow()

    private val _categoryFoods = MutableStateFlow<List<FoodEntity>>(mutableListOf())
    val categoryFoods = _categoryFoods.asStateFlow()

    private val _foodsHaveFoodId = MutableStateFlow(false)
    val foodsHaveFoodId = _foodsHaveFoodId.asSharedFlow()


    init {
        observeCategories()
        observeSubCategories()
        fetchCategoryWithSubCategories()
        observeFoods()
    }

    private fun observeCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryDao.observeCategories().collect {
                _categories.value = it
            }
        }
    }

    private fun observeSubCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryDao.observeSubCategories().collect {
                _subCategories.emit(it)
            }
        }
    }

    fun fetchCategoryWithSubCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = { categoriesApi.getCategories() },
                onDataReady = { response ->
                    val it = response.data
                    // for category remove tets => it.subList(2,it.lastIndex)
                    val categoriesWithSubCategories = it.map { categoryResponse ->
                        categoryResponse.toCategoryEntity()
                    }
                    val categories = categoriesWithSubCategories.map {
                        it[0]
                    }
                    val subCategoriesTwoDimension = categoriesWithSubCategories.map {
                        it.filterIndexed { index, categoryEntity ->
                            index >= 1 && index <= it.lastIndex
                        }
                    }
                    val subCategories: MutableList<CategoryEntity> = mutableListOf()
                    subCategoriesTwoDimension.forEach {
                        subCategories.addAll(it)
                    }

                    val cIds = categories.map {
                        it.id
                    }
                    _categories.value.forEach {
                        if (!cIds.contains(it.id)) {
                            deleteCategory(it)
                        }
                    }
                    _categories.value = categories
                    _subCategories.value = subCategories
                    storeCategoriesOrSubCategories(categories)
                    storeCategoriesOrSubCategories(subCategories)
                }
            ).collect(_categoryResult)
        }
    }

    private fun storeCategoriesOrSubCategories(categoriesOrSubCategories: List<CategoryEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryDao.insertCategoriesOrSubCategories(categoriesOrSubCategories)
        }
    }

    fun updateCategorySelectedIndex(newIndex: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _selectedCategoryIndex.emit(newIndex)
        }
    }

    fun updateSubCategorySelectedIndex(newIndex: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _selectedSubCategoryIndex.emit(newIndex)
        }
    }

    private fun observeFoods() {
        viewModelScope.launch(Dispatchers.IO) {
            foodDao.getFoods().collect {
                _foods.value = it
            }
        }
    }

    fun filterFoods(categoryEntity: CategoryEntity) {
        viewModelScope.launch(Dispatchers.Default) {
            _categoryFoods.emit(_foods.value.filter {
                categoryEntity.subCategories?.contains(it.categoryId) == true || categoryEntity.id == it.categoryId
            })
        }
    }

    fun deleteCategory(categoryEntity: CategoryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryDao.removeCategory(categoryEntity)
        }
    }

    fun fetchFoodsByCategoryId(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    foodApi.getFoodsByCategoryId(id)
                },
                onDataReady = { foodsResponse ->
                    val foodEntities = foodsResponse.data.map { foodResponse ->
                        foodResponse.toFoodEntity()
                    }
                    _categoryFoods.value = foodEntities
                    storeFoods(foodEntities)
                }
            ).collect(_foodResult)
        }
    }

    private fun storeFoods(foodEntities: List<FoodEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            foodDao.insertIntoFoods(foodEntities)
        }
    }

    fun ifFoodsHaveSpecialId(id: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val foodsWithCategoryId = _foods.value.filter {
                it.categoryId == id
            }
            _foodsHaveFoodId.value = foodsWithCategoryId.isNotEmpty()
        }
    }
}