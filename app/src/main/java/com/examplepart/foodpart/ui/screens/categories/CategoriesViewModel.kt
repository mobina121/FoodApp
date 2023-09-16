package com.examplepart.foodpart.ui.screens.categories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examplepart.foodpart.database.categories.CategoryDao
import com.examplepart.foodpart.database.categories.CategoryEntity
import com.examplepart.foodpart.network.categories.CategoriesApi
import com.examplepart.foodpart.network.common.Result
import com.examplepart.foodpart.network.common.safeApi
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
    private val categoriesApi: CategoriesApi
) : ViewModel() {
//    private var _selectedCategoryIndex = MutableStateFlow<Int>(0)
//    val selectedCategoryIndex = _selectedCategoryIndex.asStateFlow()
//
//    private val _selectedSubCategoryIndex = MutableStateFlow<Int>(0)
//    val selectedSubCategoryIndex = _selectedSubCategoryIndex.asStateFlow()


    private val _categories = MutableStateFlow<List<CategoryEntity>>(emptyList())
    val categories = _categories.asStateFlow()


    private val _subCategories = MutableStateFlow<List<CategoryEntity>>(emptyList())
    val subCategories = _subCategories.asStateFlow()

    private val _categoryResult = MutableStateFlow<Result>(Result.Idle)
    val categoryResult = _categoryResult.asSharedFlow()


    init {
        observeCategories()
        observeSubCategories()
        fetchCategoryWithSubCategories()
    }

    private fun observeCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryDao.observeCategories().collect {
                _categories.emit(it)
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

    private fun fetchCategoryWithSubCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = { categoriesApi.getCategories() },
                onDataReady = {response->
                    val it = response.data
//                    Log.d("test",it.joinToString {
//                        it.name
//                    })
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
                    _categories.value = categories
                    _subCategories.value = subCategories
                    // Todo add remove element
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

}