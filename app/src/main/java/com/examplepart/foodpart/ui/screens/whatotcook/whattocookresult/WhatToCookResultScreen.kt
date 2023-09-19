package com.examplepart.foodpart.ui.screens.whatotcook.whattocookresult

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.core.AppScreens
import com.examplepart.foodpart.core.Difficulty
import com.examplepart.foodpart.network.common.Result
import com.examplepart.foodpart.ui.common.FoodItem
import com.examplepart.foodpart.ui.common.FoodPartAppBar
import com.examplepart.foodpart.ui.common.ShowError

@Composable
fun WhatToCookResultScreen(
    whatToCookResultViewModel: WhatToCookResultViewModel,
    navController: NavController
) {

    WhatToCookResultScreenContent(
        whatToCookResultViewModel,
        onClickStareIcon = {
            navController.navigate(AppScreens.WhatToCook.route)
        },
        ocClickFood = {
            navController.navigate(AppScreens.FoodDetail.route)
        }
    )
}

@Composable
fun WhatToCookResultScreenContent(
    whatToCookResultViewModel: WhatToCookResultViewModel,
    onClickStareIcon: () -> Unit,
    ocClickFood: () -> Unit
) {
    val ingredients = whatToCookResultViewModel.ingredients
    val timeLimit = whatToCookResultViewModel.timeLimit
    val foodsList by whatToCookResultViewModel.foodsList.collectAsState()
    val foodsResult by whatToCookResultViewModel.foodsResult.collectAsState(Result.Idle)
    val isFabVisible by whatToCookResultViewModel.isFabVisible.collectAsState(false)

    var isScrolling by remember { mutableStateOf(false) }
    val scrollState = rememberLazyGridState()

    val formattedResult = stringResource(
        R.string.searchResults,
        ingredients,
        timeLimit,
        whatToCookResultViewModel.difficulty ?: Difficulty.NO_MATTER
    )

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.isScrollInProgress }
            .collect { scrolling ->
                if (scrolling) {
                    whatToCookResultViewModel.toggleFabVisibility(true)
                }
                isScrolling = scrolling
            }
    }

    LaunchedEffect(foodsResult) {
        when (foodsResult) {
            is Result.Error -> {
                // Handle error state if needed
            }

            Result.Idle -> {
                // Handle idle state if needed
            }

            Result.Loading -> {
                // Handle loading state if needed
            }

            Result.Success -> {
                // Handle success state if needed
            }
        }
    }

    Scaffold(
        topBar = {
            FoodPartAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 0.dp),
                title = stringResource(id = R.string.whatToCook),
                showStartIcon = true,
                showEndIcon = false,
                startIcon = {

                    IconButton(onClick = {
                        onClickStareIcon()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_right),
                            contentDescription = "arrow forward icon",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            if (isFabVisible && foodsList.isNotEmpty()) {
                FloatingActionButton(
                    onClick = {
                        whatToCookResultViewModel.scrollListToTop(scrollState)
                    },
                    modifier = Modifier
                        .padding(16.dp),
                    backgroundColor = MaterialTheme.colors.primary,
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_forward),
                            contentDescription = "FAB Icon",
                        )
                    }
                )
            }
        }

    ) { paddingValues ->
        if (foodsResult is Result.Loading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (foodsList.isEmpty()) {
            ShowError(
                errorMessage = stringResource(id = R.string.foodCategoriesNotFound),
                buttonTitle = stringResource(id = R.string.retry)
            ) {
                //doRetry
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 10.dp),
                text = formattedResult,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onBackground
            )

            LazyVerticalGrid(
                modifier = Modifier.padding(horizontal = 36.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                columns = GridCells.Fixed(2),
                state = scrollState,
            ) {
                items(foodsList) { foodEntity ->
                    FoodItem(
                        modifier = Modifier,
                        food = foodEntity,
                    ) {
                        ocClickFood()
                    }

                }
            }
        }
    }
}



