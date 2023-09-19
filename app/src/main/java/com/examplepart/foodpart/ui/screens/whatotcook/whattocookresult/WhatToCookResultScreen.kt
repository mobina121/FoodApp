package com.examplepart.foodpart.ui.screens.whatotcook.whattocookresult

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.core.AppScreens
import com.examplepart.foodpart.core.Difficulty
import com.examplepart.foodpart.network.common.Result
import com.examplepart.foodpart.ui.common.FoodItem
import com.examplepart.foodpart.ui.common.FoodPartAppBar
import com.examplepart.foodpart.ui.common.ShowError
import kotlinx.coroutines.launch

@Composable
fun WhatToCookResultScreen(
    whatToCookResultViewModel: WhatToCookResultViewModel,
    navController: NavController
) {

    WhatToCookResultScreenContent(
        whatToCookResultViewModel,
        onClickStareIcon = {
            navController.navigateUp()
        },
        ocClickFood = {
            navController.navigate(AppScreens.FoodDetail.createRoute(it))
        },
        doRetry = {
            whatToCookResultViewModel.findWhatToCook()
        },
    )
}

@Composable
fun WhatToCookResultScreenContent(
    whatToCookResultViewModel: WhatToCookResultViewModel,
    onClickStareIcon: () -> Unit,
    ocClickFood: (foodId: String) -> Unit,
    doRetry: () -> Unit
) {

    val listState = rememberLazyGridState()
    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    val ingredients = whatToCookResultViewModel.ingredients
    val timeLimit = whatToCookResultViewModel.timeLimit
    val foodsList by whatToCookResultViewModel.foodsList.collectAsState()
    val foodsResult by whatToCookResultViewModel.foodsResult.collectAsState(Result.Idle)

    val scope = rememberCoroutineScope()


    val formattedResult = stringResource(
        R.string.searchResults,
        ingredients,
        timeLimit,
        whatToCookResultViewModel.difficulty ?: Difficulty.NO_MATTER
    )

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
            AnimatedVisibility(
                visible = showButton,
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
        if (foodsResult is Result.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),

                )
            {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .background(White)
                )
            }
        }
        if (foodsResult is Result.Error) {
            ShowError(
                errorMessage = stringResource(id = R.string.serverMessageError),
                buttonTitle = stringResource(id = R.string.retry)
            ) {
                doRetry()
            }
        }
        if (foodsResult is Result.Success) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                if (foodsList.isEmpty()) {
                    ShowError(
                        errorMessage = stringResource(id = R.string.foodCategoriesNotFound),
                        buttonTitle = stringResource(id = R.string.retry)
                    ) {
                        doRetry()
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 0.dp, 20.dp, 16.dp),
                        text = formattedResult,
                        lineHeight = 20.sp,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onBackground
                    )

                    LazyVerticalGrid(
                        modifier = Modifier.padding(horizontal = 36.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        columns = GridCells.Fixed(2),
                        state = listState
                    ) {
                        items(foodsList) { foodEntity ->
                            FoodItem(
                                modifier = Modifier,
                                food = foodEntity,
                            ) {
                                Log.d("foodEntity.id", "$foodEntity.id")
                                ocClickFood(foodEntity.id)
                            }

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



