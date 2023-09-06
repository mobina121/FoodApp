package com.examplepart.foodpart.ui.screens.food

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.datamodel.fakeData
import com.examplepart.foodpart.ui.common.CustomChip
import com.examplepart.foodpart.ui.common.CustomDropdownMenuItem
import com.examplepart.foodpart.ui.common.FoodAppBar
import com.examplepart.foodpart.ui.common.FoodItem
import com.examplepart.foodpart.ui.common.PhotoOfFood
import com.examplepart.foodpart.ui.common.SubCategory
import com.examplepart.foodpart.ui.core.AppScreens
import kotlinx.coroutines.launch
import androidx.compose.material.Text as Text1


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FoodDetailScreen(navController: NavController) {

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        content = {
            FoodDetailScreenContent(
                navController = navController,
                sendReport = {
                    scope.launch {
                        bottomSheetState.show()
                    }
                }
            )
        },
        sheetContent = {
            Report(
                bottomSheetState = bottomSheetState,
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
    navController: NavController,
    sendReport: () -> Unit
) {
    var isDropDownMenuShowing: Boolean by remember {
        mutableStateOf(false)
    }

    var isLoggin by remember { mutableStateOf(true) }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val msg = stringResource(id = R.string.theRecipeHasBeenAddedToFavorites)
    val actionLabel = stringResource(id = R.string.saved)

    Scaffold(
        topBar = {
            FoodAppBar(
                modifier = Modifier.padding(horizontal = 15.dp),
                title = {
                    Text(
                        text = stringResource(R.string.food_details),
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onBackground,
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            isDropDownMenuShowing = !isDropDownMenuShowing
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_more),
                            contentDescription = "more icon",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                    DropdownMenu(
                        expanded = isDropDownMenuShowing,
                        onDismissRequest = {
                            isDropDownMenuShowing = false
                        }) {
                        CustomDropdownMenuItem(
                            title = stringResource(R.string.report),
                            iconId = R.drawable.ic_report,
                        ) {
                            sendReport()
                            isDropDownMenuShowing = false
                        }
                        CustomDropdownMenuItem(
                            title = stringResource(R.string.send),
                            iconId = R.drawable.ic_share
                        ) {}
                        CustomDropdownMenuItem(
                            title = stringResource(R.string.save),
                            iconId = R.drawable.ic_bookmark
                        ) {
                            if (isLoggin) {
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
                                            navController.navigate(AppScreens.Categories.route)
                                        }

                                        else -> {}
                                    }
                                }

                            } else {
                                navController.navigate(AppScreens.Signup.route)
                                isDropDownMenuShowing = false
                                isLoggin = true
                            }

                        }
                    }

                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(AppScreens.Categories.route)

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
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    actionColor = MaterialTheme.colors.primary,
                    backgroundColor = Color(0xff393939),
                    contentColor = MaterialTheme.colors.onBackground,
                    snackbarData = data
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ScreenContent(
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )
        }

    }
}


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ScreenContent(modifier: Modifier = Modifier, navController: NavController) {

    var showAllItems by remember { mutableStateOf(false) }
    val itemsToDisplay = if (showAllItems) fakeData else fakeData.take(4)
    val pageState = rememberPagerState()
    val tabs = listOf(
        stringResource(id = R.string.rawMaterial),
        stringResource(id = R.string.howToPrepare),
        stringResource(id = R.string.moreInformation)
    )

    val stateLazy = rememberLazyListState()

    val scope = rememberCoroutineScope()



    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PhotoOfFood(navController = navController, photoId = R.drawable.food_pic)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.abgoosht),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onBackground
            )
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text1(
                    text = stringResource(id = R.string.forFourPerson),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onBackground
                )
                CustomChip(
                    modifier = Modifier.fillMaxWidth(),
                    icon = R.drawable.timer,
                    label = stringResource(id = R.string.time),
                    hasColor = true,
                    color = Color(0xA0FF6262).copy(0.2f)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomChip(
                    icon = null,
                    label = stringResource(id = R.string.lunch),
                    hasColor = false,
                    color = null,
                    modifier = null
                )
                Spacer(modifier = Modifier.width(10.dp))
                CustomChip(
                    icon = null,
                    label = stringResource(id = R.string.breakfast),
                    hasColor = false,
                    color = null,
                    modifier = null
                )
            }

            SubCategory(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                icon = R.drawable.ic_leaf,
                label = stringResource(id = R.string.easy)
            )
        }
        ScrollableTabRow(
            modifier = Modifier.padding(vertical = 15.dp, horizontal = 30.dp),
            selectedTabIndex = pageState.currentPage,
            edgePadding = 0.dp,
            backgroundColor = MaterialTheme.colors.background,
            indicator = {
                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .tabIndicatorOffset(it[pageState.currentPage])
                        .height(1.dp)
                        .background(
                            MaterialTheme.colors.primary,
                            shape = MaterialTheme.shapes.large
                        )
                )
            },
            tabs = {
                tabs.forEachIndexed { index, tabNane ->
                    Tab(
                        selected = pageState.currentPage == index,
                        onClick = {
                            scope.launch { pageState.animateScrollToPage(index) }
                        },
                        selectedContentColor = MaterialTheme.colors.primary
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 10.dp),
                            text = tabNane,
                            style = MaterialTheme.typography.h3,
                            color = if (pageState.currentPage == index) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground
                        )
                    }
                }
            }
        )
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.medium)
                .padding(vertical = 10.dp, horizontal = 15.dp),
            verticalAlignment = Alignment.Top,
            pageCount = tabs.count(),
            state = pageState,
            userScrollEnabled = true,
            reverseLayout = true

        ) { page ->
            val tabText = when (page) {
                0 -> "111111 ${stringResource(id = R.string.tabText)}"
                1 -> "22222 ${stringResource(id = R.string.tabText)}"
                else -> "33333 ${stringResource(id = R.string.tabText)}"
            }

            Column(
                modifier = Modifier
                    .height(400.dp)
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
                            text = tabText,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onBackground
                        )
                    }

                }
            }
        }
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
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
                    itemsIndexed(itemsToDisplay) { foodId, food ->
                        FoodItem(food) {}
                    }
                    item() {
                        if (!showAllItems) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp, horizontal = 5.dp)
                                    .clickable {
                                        showAllItems = true
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
                                        style = MaterialTheme.typography.body1,
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
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Report(bottomSheetState: ModalBottomSheetState) {
    var reportText by remember { mutableStateOf("") }
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
            value = reportText,
            onValueChange = { text -> reportText = text },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                unfocusedBorderColor = MaterialTheme.colors.surface,
                focusedBorderColor = MaterialTheme.colors.onSurface,
            ),
            shape = MaterialTheme.shapes.medium,
            placeholder = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    text = stringResource(R.string.wrightHere),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
            },
            textStyle = MaterialTheme.typography.body1,
            maxLines = 2
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Button(
                onClick = {
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