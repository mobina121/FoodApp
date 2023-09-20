package com.examplepart.foodpart.ui.screens.categories

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.core.AppScreens
import com.examplepart.foodpart.database.categories.CategoryEntity
import com.examplepart.foodpart.network.common.ConnectionState
import com.examplepart.foodpart.network.common.Result
import com.examplepart.foodpart.network.common.connectivityState
import com.examplepart.foodpart.ui.common.FoodPartAppBar
import com.examplepart.foodpart.ui.common.FoodsList
import com.examplepart.foodpart.ui.common.ShowError
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@Composable
fun CategoriesScreen(
    navController: NavController,
    categoriesViewModel: CategoriesViewModel
) {
    val foodCategories by categoriesViewModel.categories.collectAsState()
    val subCategories by categoriesViewModel.subCategories.collectAsState()
    val selectedCategoryIndex by categoriesViewModel.selectedCategoryIndex.collectAsState()
    val selectedSubCategoryIndex by categoriesViewModel.selectedSubCategoryIndex.collectAsState()
    val categoryResult by categoriesViewModel.categoryResult.collectAsState(initial = Result.Idle)
    val foods = categoriesViewModel.categoryFoods.collectAsState(listOf()).value
    val foodResult by categoriesViewModel.foodResult.collectAsState(Result.Idle)
    val foodsHaveFoodId by categoriesViewModel.foodsHaveFoodId.collectAsState(false)
    var selectedSubCategoryEntity: CategoryEntity? = null

    val scope = rememberCoroutineScope()
    val listState = rememberLazyGridState()

    val scrollState by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }
    val connection by connectivityState()

    var errorState by rememberSaveable {
        mutableStateOf(0)
    }

    var foodErrorState by rememberSaveable {
        mutableStateOf(0)
    }
    var categoryLoading by rememberSaveable {
        mutableStateOf(false)
    }
    var foodLoading by rememberSaveable {
        mutableStateOf(false)
    }


    Scaffold(
        topBar = {
            FoodPartAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 0.dp),
                title = stringResource(id = R.string.foodPart),
                showStartIcon = false,
                showEndIcon = false,
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = scrollState,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                ScrollToTopButton(onClick = {
                    scope.launch {
                        listState.animateScrollToItem(0)
                    }
                })
            }
        }
    ) { paddingValues ->
        LaunchedEffect(categoryResult) {
            categoriesViewModel.categoryResult.collectLatest {
                if (it is Result.Error) {
                    errorState = if (it.message == "Internet") {
                        1
                    } else {
                        2
                    }
                } else if (it is Result.Loading) {
                    categoryLoading = true
                } else {
                    errorState = 0
                    categoryLoading = false
                }
            }
        }
        LaunchedEffect(foodResult) {
            categoriesViewModel.foodResult.onEach {
                if (it is Result.Error) {
                    foodErrorState = if (it.message == "Internet") {
                        1
                    } else {
                        2
                    }
                } else if (it is Result.Loading) {
                    foodLoading = true
                } else {
                    foodErrorState = 0
                    foodLoading = false
                }
            }.launchIn(this)
        }

        LaunchedEffect(key1 = selectedCategoryIndex) {
            foodCategories.getOrNull(selectedCategoryIndex)?.let {
                scope.launch {
                    listState.animateScrollToItem(index = 0)
                }
                categoriesViewModel.filterFoods(it)
                categoriesViewModel.ifFoodsHaveSpecialId(it.id)
                if (foods.isNotEmpty() && !foodsHaveFoodId && connection !== ConnectionState.Available) {
                    foodLoading = false
                    foodErrorState = 1
                } else {
                    categoriesViewModel.fetchFoodsByCategoryId(it.id)
                }
            }
        }
        LaunchedEffect(key1 = selectedSubCategoryIndex) {
            foodCategories.getOrNull(selectedCategoryIndex)?.let {
                if (it.subCategories?.contains(subCategories.getOrNull(selectedSubCategoryIndex)?.id) == true) {
                    val subCategory = subCategories[selectedSubCategoryIndex]
                    categoriesViewModel.filterFoods(subCategory)
                    categoriesViewModel.ifFoodsHaveSpecialId(subCategory.id)
                    if (foods.isNotEmpty() && !foodsHaveFoodId && connection !== ConnectionState.Available) {
                        foodErrorState = 1
                    } else {
                        categoriesViewModel.fetchFoodsByCategoryId(subCategory.id)
                    }
                }
            }
        }
        if (errorState == 1 && foodCategories.isEmpty() && connection !== ConnectionState.Available) {
            ShowError(
                errorMessage = stringResource(id = R.string.serverError),
                buttonTitle = stringResource(
                    id = R.string.retry
                )
            ) {
                categoriesViewModel.fetchCategoryWithSubCategories()
            }
        } else if (errorState == 2) {
            ShowError(
                errorMessage = stringResource(id = R.string.technicalError),
                buttonTitle = stringResource(
                    id = R.string.retry
                )
            ) {
                categoriesViewModel.fetchCategoryWithSubCategories()
            }
        } else {
            if (categoryLoading == true) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(foodCategories) { index, categoryEntity ->
                        val startPadding = if (index == 0) 16.dp else 0.dp
                        val endPadding = if (index == foodCategories.size - 1) 16.dp else 0.dp

                        FoodCategoryChip(
                            modifier = Modifier.padding(start = startPadding, end = endPadding),
                            categoryEntity = categoryEntity,
                            isSelected = selectedCategoryIndex == index
                        ) {
                            categoriesViewModel.updateCategorySelectedIndex(index)
                        }
                    }
                }

                Divider(
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 16.dp)
                        .height(0.5.dp),
                    color = MaterialTheme.colors.onSurface
                )

                val selectedCategory = foodCategories.getOrNull(selectedCategoryIndex)

                selectedCategory?.let { category ->
                    Column {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            itemsIndexed(subCategories.filter {
                                selectedCategory.subCategories?.contains(it.id) ?: false
                            }) { index, subCategoryEntity ->
                                val startPadding = if (index == 0) 16.dp else 0.dp
                                val endPadding =
                                    if (index == foodCategories.size - 1) 16.dp else 0.dp

                                SubFoodCategoryChip(
                                    modifier = Modifier.padding(
                                        start = startPadding,
                                        end = endPadding
                                    ),
                                    subCategoryEntity = subCategoryEntity,
                                    isSelected = selectedSubCategoryIndex == index
                                ) {
                                    scope.launch {
                                        listState.animateScrollToItem(index = 0)
                                    }
                                    selectedSubCategoryEntity = subCategoryEntity
                                    categoriesViewModel.updateSubCategorySelectedIndex(index)
                                }
                            }
                        }
                        if ((category.subCategories?.size ?: 0) > 0) {
                            Divider(
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 16.dp)
                                    .height(0.5.dp),
                                color = MaterialTheme.colors.onSurface
                            )
                        }
                    }
                    val categoryId = selectedSubCategoryEntity ?: category

                    if (foodErrorState == 1 && foods.isNotEmpty() && !foodsHaveFoodId) {
                        ShowError(
                            errorMessage = stringResource(id = R.string.serverError),
                            buttonTitle = stringResource(
                                id = R.string.retry
                            )
                        ) {
                            categoriesViewModel.filterFoods(categoryId)
                            categoriesViewModel.fetchFoodsByCategoryId(categoryId.id)

                            foodErrorState = 0
                        }
                    } else if (foodErrorState == 2) {
                        ShowError(
                            errorMessage = stringResource(id = R.string.technicalError),
                            buttonTitle = stringResource(
                                id = R.string.retry
                            )
                        ) {
                            categoriesViewModel.filterFoods(categoryId)
                            categoriesViewModel.fetchFoodsByCategoryId(categoryId.id)
                        }
                    } else {
                        if (foodLoading == true && categoryLoading != true) {
                            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                        }
                        FoodsList(items = foods, state = listState) {
                            navController.navigate(AppScreens.FoodDetail.createRoute(it))
                        }
                    }
                }
            }
        }
    }


}

@Composable
fun ScrollToTopButton(onClick: () -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, end = 16.dp), Alignment.BottomEnd
    ) {
        Button(
            onClick = {
                onClick()
            },
            modifier = Modifier
                .shadow(10.dp, shape = CircleShape)
                .clip(shape = CircleShape)
                .size(55.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_forward),
                contentDescription = "FAB Icon",
            )
        }
    }
}
