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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.datamodel.FoodItemModel
import com.examplepart.foodpart.datamodel.fakeData
import com.examplepart.foodpart.ui.common.FoodItem
import com.examplepart.foodpart.ui.common.FoodPartAppBar
import com.examplepart.foodpart.ui.common.ShowError
import com.examplepart.foodpart.core.AppScreens
import kotlinx.coroutines.launch

@Composable
fun WhatToCookResultScreen(navController: NavController) {
    val foods = fakeData

    WhatToCookResultScreenContent(
        foods,
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
    foodsList: List<FoodItemModel>,
    onClickStareIcon: () -> Unit,
    ocClickFood: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyGridState()

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
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        scrollState.animateScrollToItem(0)
                    }

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

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 10.dp),
                text = "${stringResource(id = R.string.resultFor)}\n${stringResource(id = R.string.extraText)}",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onBackground
            )

            if (foodsList.isNotEmpty()) {
                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = 36.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    columns = GridCells.Fixed(2),
                    state = scrollState,
                ) {
                    items(foodsList) { food ->
                        FoodItem(
                            modifier = Modifier,
                            food = food,
                        ) {
                            ocClickFood()
                        }
                    }
                }
            } else {
                ShowError(
                    errorMessage = stringResource(id = R.string.foodCategoriesNotFound),
                    buttonTitle = stringResource(id = R.string.retry)
                ) {
                    //doRetry
                }
            }
        }
    }
}
