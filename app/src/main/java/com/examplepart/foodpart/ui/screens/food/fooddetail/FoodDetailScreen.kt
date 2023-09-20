package com.examplepart.foodpart.ui.screens.food.fooddetail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.core.AppScreens
import com.examplepart.foodpart.network.common.Result
import com.examplepart.foodpart.ui.common.CustomChip
import com.examplepart.foodpart.ui.common.CustomDropdownMenuItem
import com.examplepart.foodpart.ui.common.FoodItem
import com.examplepart.foodpart.ui.common.FoodPartAppBar
import com.examplepart.foodpart.ui.common.PhotoOfFood
import com.examplepart.foodpart.ui.common.ShowError
import com.examplepart.foodpart.ui.common.SimpleChip
import com.examplepart.foodpart.ui.common.SubCategory
import com.examplepart.foodpart.ui.theme.DarkRed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.compose.material.Text as Text1


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FoodDetailScreen(
    foodDetailViewModel: FoodDetailViewModel,
    navController: NavController
) {

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        foodDetailViewModel.foodDetailResult.collectLatest { result ->
            when (result) {
                is Result.Error -> {}

                Result.Idle,
                Result.Loading -> {
                }

                Result.Success -> {
                    foodDetailViewModel.fetchFoodsByIds()
                }
            }
        }
    }


    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        content = {
            FoodDetailScreenContent(
                foodDetailViewModel = foodDetailViewModel,
                navigateFullScreenPhoto = {
                    navController.navigate(AppScreens.FullscreenImage.createRoute(it))
                },
                onClickStartIcon = {
                    navController.navigateUp()
                },
                onClickReport = {
                    scope.launch {
                        bottomSheetState.show()
                    }
                },

                onClickShare = {},
                navigateToSavedScreen = {
                    navController.navigate(AppScreens.Saved.route)
                },
                navigateToSignup = {
                    navController.navigate(AppScreens.Signup.route)
                },
                navigateCategories = {
                    navController.navigate(
                        AppScreens.FoodsByCategoryResult.createRoute(
                            foodDetailViewModel.foodDetail.value.categoryId
                        )
                    )
                },
                navigateFoodByMeelResult = {
                    navController.navigate(
                        AppScreens.FoodsByMealResult.createRoute(it)
                    )
                },


                )
        },
        sheetContent = {
            Report(
                foodDetailViewModel = foodDetailViewModel,
                bottomSheetState = bottomSheetState
            )
        },
        sheetShape = MaterialTheme.shapes.large.copy(
            bottomStart = CornerSize(0.dp),
            bottomEnd = CornerSize(0.dp),
        ),
        sheetElevation = 4.dp,
    )
}


@Composable
private fun FoodDetailScreenContent(
    foodDetailViewModel: FoodDetailViewModel,
    onClickReport: () -> Unit,
    onClickShare: () -> Unit,
    onClickStartIcon: () -> Unit,
    navigateFullScreenPhoto: (photoId: String) -> Unit,
    navigateFoodByMeelResult: (mealId: String) -> Unit,
    navigateCategories: () -> Unit,
    navigateToSavedScreen: () -> Unit,
    navigateToSignup: () -> Unit,
) {

    var isDropDownMenuShowing: Boolean by remember {
        mutableStateOf(false)
    }
    var isLogin by remember { mutableStateOf(true) }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val msg = stringResource(id = R.string.theRecipeHasBeenAddedToFavorites)
    val actionLabel = stringResource(id = R.string.saved)

    val foodDetailResult by foodDetailViewModel.foodDetailResult.collectAsState(Result.Idle)

    LaunchedEffect(Unit) {
        foodDetailViewModel.reportResult.collectLatest { result ->
            when (result) {
                is Result.Error -> {}

                Result.Idle,
                Result.Loading -> {
                }

                Result.Success -> {
                    Log.d("REPORT RESULT", "${foodDetailViewModel.reportResult}")
                    scope.launch {
                        val snackbarResult =
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = foodDetailViewModel.reportAnswer.value,
                                duration = SnackbarDuration.Long
                            )

                        when (snackbarResult) {
                            SnackbarResult.Dismissed -> {}
                            else -> {}
                        }
                    }

                }
            }
        }
    }



    Scaffold(
        topBar = {
            FoodPartAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                title = stringResource(id = R.string.food_details),
                showStartIcon = true,
                showEndIcon = true,
                startIcon = {
                    IconButton(onClick = {
                        onClickStartIcon()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_right),
                            contentDescription = "arrow forward icon",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                },
                endIcon = {
                    IconButton(
                        onClick = {
                            isDropDownMenuShowing = !isDropDownMenuShowing
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_more),
                            contentDescription = "more icon",
                            tint = MaterialTheme.colors.onBackground
                        )
                        DropdownMenu(
                            expanded = isDropDownMenuShowing,
                            onDismissRequest = {
                                isDropDownMenuShowing = false
                            },
                            modifier = Modifier.width(162.dp),
                        )
                        {
                            CustomDropdownMenuItem(
                                title = stringResource(R.string.report),
                                iconId = R.drawable.ic_report,
                            ) {
                                onClickReport()
                                isDropDownMenuShowing = false
                            }
                            CustomDropdownMenuItem(
                                title = stringResource(R.string.send),
                                iconId = R.drawable.ic_share
                            ) {
                                onClickShare()
                            }
                            CustomDropdownMenuItem(
                                title = stringResource(R.string.save),
                                iconId = R.drawable.ic_bookmark
                            ) {
                                if (isLogin) {
                                    scope.launch {
                                        val snackbarResult =
                                            scaffoldState.snackbarHostState.showSnackbar(
                                                message = msg,
                                                actionLabel = actionLabel,
                                                duration = SnackbarDuration.Short
                                            )

                                        when (snackbarResult) {
                                            SnackbarResult.Dismissed -> {}
                                            SnackbarResult.ActionPerformed -> {
                                                navigateToSavedScreen()

                                            }

                                            else -> {}
                                        }
                                    }
                                    isDropDownMenuShowing = false
                                } else {
                                    navigateToSignup()
                                    isDropDownMenuShowing = false
                                    isLogin = true
                                }

                            }
                        }
                    }

                }
            )
        },
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    actionColor = MaterialTheme.colors.primary,
                    backgroundColor = DarkGray,
                    contentColor = MaterialTheme.colors.onBackground,
                    snackbarData = data
                )
            }
        }
    ) { paddingValues ->

        if (foodDetailResult is Result.Loading) {
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
                        .background(Color.White)
                )
            }
        }

        if (foodDetailResult is Result.Error) {
            ShowError(
                errorMessage = stringResource(id = R.string.serverMessageError),
                buttonTitle = stringResource(id = R.string.retry)
            ) {
                //doRetry()
            }
        }

        if (foodDetailResult is Result.Success) {

            ScreenContent(
                foodDetailViewModel = foodDetailViewModel,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                showFullScreenPhoto = {
                    navigateFullScreenPhoto(it)
                },
                onShowMoreCategory = {
                    navigateCategories()
                },
                onClickMeal = {
                    val mealId = foodDetailViewModel.findMealIdByMeal(it)
                    navigateFoodByMeelResult(mealId)
                }
            )
        }


    }
}


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenContent(
    foodDetailViewModel: FoodDetailViewModel,
    modifier: Modifier,
    showFullScreenPhoto: (foodId: String) -> Unit,
    onShowMoreCategory: () -> Unit,
    onClickMeal: (meal: String) -> Unit,
) {
    val foodDetail by foodDetailViewModel.foodDetail.collectAsState()
    val meals by foodDetailViewModel.meals.collectAsState()
    val difficulty by foodDetailViewModel.difficultyLevel.collectAsState()
    val similarFoods by foodDetailViewModel.similarFoods.collectAsState()
    val tabsData by foodDetailViewModel.tabsData.collectAsState()
    val currentPage by foodDetailViewModel.currentPage.collectAsState()

    val pageState = rememberPagerState()

    val stateLazy = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PhotoOfFood(
            photoId = foodDetail.image,
            onClickPhoto = {
                showFullScreenPhoto(it)
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 8.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = foodDetail.name,
                style = if (foodDetail.name.length < 15) MaterialTheme.typography.h1 else MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onBackground
            )

            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (foodDetail.count != null) {
                    Text1(
                        text = "${stringResource(id = R.string.forCount)} ${foodDetail.count}",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                if (foodDetail.readyTime != null || foodDetail.cookTime != null) {
                    val readyTime = foodDetail.readyTime ?: 0
                    val cookTime = foodDetail.cookTime ?: 0
                    val totalTime = readyTime.plus(cookTime).toString()
                    CustomChip(
                        icon = {
                            Image(
                                painterResource(R.drawable.timer),
                                modifier = Modifier
                                    .padding(0.dp, 0.dp, 10.dp, 0.dp)
                                    .size(18.dp),
                                contentDescription = "",
                                contentScale = ContentScale.FillWidth,
                            )
                        },
                        label = "$totalTime ${stringResource(id = R.string.time)}",
                        color = DarkRed,
                    )
                }

            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            LazyRow(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                items(meals) { meal ->
                    SimpleChip(
                        label = meal,
                    ) {
                        onClickMeal(meal)
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                }

            }
            difficulty?.let {
                SubCategory(label = it)
            }
        }
        val density = LocalDensity.current
        val tabWidths = remember {
            val tabWidthStateList = mutableStateListOf<Dp>()
            repeat(tabsData.size) {
                tabWidthStateList.add(0.dp)
            }
            tabWidthStateList
        }

        if (tabsData.isNotEmpty() && currentPage in 0 until tabsData.size) {
            TabRow(
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 20.dp)
                    .fillMaxWidth(0.7f)
                    .align(Alignment.Start),
                selectedTabIndex = currentPage,
                backgroundColor = MaterialTheme.colors.background,
                indicator = {
                    TabRowDefaults.Indicator(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .height(1.dp)
                            .customTabIndicatorOffset(
                                currentTabPosition = it[pageState.currentPage],
                                tabWidth = tabWidths[pageState.currentPage]
                            ),
                        color = MaterialTheme.colors.primary
                    )
                },
                divider = {},
            ) {
                tabsData.keys.forEachIndexed { index, tabName ->
                    Tab(
                        selected = currentPage == index,
                        selectedContentColor = MaterialTheme.colors.primary,
                        onClick = {
                            foodDetailViewModel.onTabSelected(index)
                        }
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 10.dp),
                            text = tabName,
                            onTextLayout = { textLayoutResult ->
                                tabWidths[pageState.currentPage] =
                                    with(density) { textLayoutResult.size.width.toDp() }
                            },
                            style = MaterialTheme.typography.h3,
                            color = if (pageState.currentPage == index) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground
                        )
                    }
                }
            }
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .padding(vertical = 10.dp, horizontal = 15.dp),
                verticalAlignment = Alignment.Top,
                pageCount = tabsData.count(),
                state = pageState,
                userScrollEnabled = true,
                reverseLayout = true
            ) { page ->
                val tabValue = tabsData.values.elementAt(page)
                Column(
                    modifier = Modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colors.surface)
                        .padding(vertical = 20.dp, horizontal = 15.dp)

                ) {
                    LazyColumn(
                        state = stateLazy,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        item {
                            Text(
                                text = tabValue,
                                style = MaterialTheme.typography.subtitle1,
                                color = MaterialTheme.colors.onBackground,
                                lineHeight = 20.sp
                            )
                        }
                    }
                }
            }
        }

        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 10.dp),
                text = stringResource(id = R.string.more),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onBackground
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(similarFoods) { food ->
                    FoodItem(
                        modifier = Modifier,
                        food = food,
                    ) {

                    }

                }
                item {
                    ShowMoreButton {
                        onShowMoreCategory()
                    }

                }
            }
        }

    }
}

@Composable
private fun ShowMoreButton(
    onShowMoreCategory: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onShowMoreCategory()
            }
            .size(width = 136.dp, height = 80.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 2.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = stringResource(R.string.showMore),
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onBackground,
            )
            Spacer(
                modifier = Modifier.size(10.dp)
            )
            Icon(
                modifier = Modifier.size(30.dp),
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "arrow forward icon",
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Report(
    foodDetailViewModel: FoodDetailViewModel,
    bottomSheetState: ModalBottomSheetState
) {
    val description = foodDetailViewModel.descriptionReport.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(
                vertical = 15.dp,
                horizontal = 24.dp
            )
    ) {
        Text(
            text = stringResource(R.string.commandReport),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h3,
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth()
                .height(88.dp),
            value = description.value,
            onValueChange = {
                foodDetailViewModel.updateDescriptionReport(it)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                unfocusedBorderColor = MaterialTheme.colors.surface,
                focusedBorderColor = MaterialTheme.colors.onSurface,
                cursorColor = MaterialTheme.colors.onSurface
            ),
            shape = MaterialTheme.shapes.medium,
            placeholder = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    text = stringResource(R.string.wrightHere),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface
                )
            },
            textStyle = MaterialTheme.typography.subtitle1,
            maxLines = 2
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Button(
                onClick = {
                    foodDetailViewModel.sendReportFood()
                    scope.launch {

                        bottomSheetState.hide()
                    }
                },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary),
                modifier = Modifier
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = stringResource(id = R.string.record),
                    style = MaterialTheme.typography.button,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "customTabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing), label = ""
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing), label = ""
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}