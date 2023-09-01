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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
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
import com.examplepart.foodpart.ui.common.CustomChip
import com.examplepart.foodpart.ui.common.CustomDropdownMenuItem
import com.examplepart.foodpart.ui.common.FoodAppBar
import com.examplepart.foodpart.ui.common.FoodItem
import com.examplepart.foodpart.ui.common.PhotoOfFood
import com.examplepart.foodpart.ui.common.SubCategory
import com.examplepart.foodpart.ui.core.AppScreens
import com.examplepart.foodpart.ui.datamodels.fakeData
import kotlinx.coroutines.launch
import androidx.compose.material.Text as Text1


@Composable
fun FoodDetailScreen(navController: NavController) {
    var isDropDownMenuShowing: Boolean by remember {
        mutableStateOf(false)
    }

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
                    IconButton(onClick = {
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
                            onClicked = {})
                        CustomDropdownMenuItem(
                            title = stringResource(R.string.send),
                            iconId = R.drawable.ic_share,
                            onClicked = {})
                        CustomDropdownMenuItem(
                            title = stringResource(R.string.save),
                            iconId = R.drawable.ic_bookmark,
                            onClicked = {})
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
    val tabs = listOf("مواد اولیه", "طرز تهیه", "اطلاعات بیشتر")
    var stateLazy = rememberLazyListState()

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
                    label = stringResource(id = R.string.breakfast),
                    hasColor = false,
                    color = null,
                    modifier = null
                )
                Spacer(modifier = Modifier.width(10.dp))
                CustomChip(
                    icon = null,
                    label = stringResource(id = R.string.lunch),
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
                        FoodItem(food)
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
