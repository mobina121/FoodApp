package com.examplepart.foodpart.ui.screens.food

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.ui.common.CustomDropdownMenuItem
import com.examplepart.foodpart.ui.common.FoodAppBar
import com.examplepart.foodpart.ui.common.FoodAppBar
import com.examplepart.foodpart.ui.common.PhotoOfFood
import com.examplepart.foodpart.ui.core.AppScreens
import kotlinx.coroutines.launch
import androidx.compose.material.Text as Text1

class Food(name: String, time: String) {}

@OptIn(ExperimentalFoundationApi::class)
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
                            painter = painterResource(id = R.drawable.more_horiz),
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
                            modifier = Modifier.size(18.dp),
                            painter = painterResource(id = R.drawable.arrow_back),
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


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ScreenContent(modifier: Modifier = Modifier, navController: NavController) {
    val fakeData = listOf(
        Food("سیبزمینی", "20 دقیقه"),
        Food("سیبزمینی", "20 دقیقه"),
        Food("سیبزمینی", "20 دقیقه"),
        Food("سیبزمینی", "20 دقیقه"),
        Food("سیبزمینی", "20 دقیقه"),
        Food("سیبزمینی", "20 دقیقه"),
        Food("سیبزمینی", "20 دقیقه"),
        Food("سیبزمینی", "20 دقیقه"),
        Food("سیبزمینی", "20 دقیقه"),
        Food("سیبزمینی", "20 دقیقه"),
    )
    val pageState = rememberPagerState()
    val paperPage = remember {
        mutableStateOf(0)
    }
    val collectionTaps = arrayListOf("اطلاعات بیشتر", "طرز تهیه", "مواد اولیه")
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
                .padding(vertical = 10.dp, horizontal = 15.dp)
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text1(
                    text = stringResource(id = R.string.abgoosht),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text1(
                    text = stringResource(id = R.string.forFourPerson),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onBackground
                )
                Spacer(modifier = Modifier.width(20.dp))

//                Chip(
//                    modifier = Modifier
//                        .clip(RoundedCornerShape(16.dp))
//                        .alpha(0.5f)
//                        .background(Color(0xA0FF6262).copy(0.2f)),
//                    border = BorderStroke(0.dp, Color.Red),
//                    leadingIcon = {
//                        Icon(
//                            modifier = Modifier.size(18.dp),
//                            painter = painterResource(id = R.drawable.ic_timer_new),
//                            contentDescription = "timer icon",
//                        )
//                    },
//                    onClick = {}) {
//                    Text(
//                        text = stringResource(id = R.string.time),
//                        textAlign = TextAlign.End,
//                        style = MaterialTheme.typography.caption,
//                        color = MaterialTheme.colors.onBackground
//                    )
//                }

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(MaterialTheme.shapes.medium)
                        .background(Color(0xA0FF6262).copy(0.2f))
                        .padding(vertical = 5.dp, horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painterResource(R.drawable.timer),
                        modifier = Modifier.size(20.dp),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                    )
                    Text1(
                        text = stringResource(id = R.string.time),
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onBackground
                    )


                }


            }


        }
        //------------------------------------------------------------------------------------------------------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 15.dp)
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            Row(
                modifier = Modifier
                    .weight(1f),
//                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xA0FF6262).copy(0.2f))
                        .padding(vertical = 5.dp, horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text1(
                        text = "آسان",
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.surface
                    )
                    Spacer(modifier = Modifier.width(20.dp))

                    Image(
                        painterResource(R.drawable.ic_leaf),
                        modifier = Modifier.size(25.dp),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.width(20.dp))

            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .fillMaxWidth(),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text1(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colors.primary)
                        .padding(vertical = 10.dp, horizontal = 20.dp),
                    text = "صبحانه",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.surface
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text1(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colors.primary)
                        .padding(vertical = 10.dp, horizontal = 20.dp),
                    text = "ناهار",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.surface
                )


            }

        }
        //---------------------------------------------------------------------------
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(vertical = 10.dp, horizontal = 15.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(MaterialTheme.colors.primary),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        )
        {
            ScrollableTabRow(
                selectedTabIndex = minOf(collectionTaps.count(), paperPage.value),
                edgePadding = 20.dp,
                contentColor = MaterialTheme.colors.secondary,
                tabs = {
                    collectionTaps.forEachIndexed { index, tabNane ->
                        Tab(
                            modifier = Modifier.background(MaterialTheme.colors.primary),
                            onClick = {
                                paperPage.value = index
                                scope.launch { pageState.currentPage }
                            },

                            text = { Text1(tabNane) },
                            selected = index == pageState.currentPage,
                        )
                    }
                }
            )
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(25.dp))
                    .padding(vertical = 10.dp, horizontal = 15.dp),
                verticalAlignment = Alignment.Top,
                pageCount = collectionTaps.count(),
                state = pageState,
                userScrollEnabled = true,
                reverseLayout = true
            ) {
                Text1(
                    text = ".دﺮﯿﮔ راﺮﻗ هدﺎﻔﺘﺳا درﻮﻣ ﺎﺳﺎﺳا ﯽﺣاﺮﻃ دﻮﺟﻮﻣ یﺎﯿﻧد ﻞﻫا ﻪﺘﺳﻮﯿﭘ تﺎﻟاﻮﺳ یﻮﮕﺑاﻮﺟ و ﯽﻠﺻا یﺎﻫدروﺎﺘﺳد ﯽﻨﯿﭽﻓوﺮﺣ ﻞﻣﺎﺷ زﺎﯿﻧ درﻮﻣ نﺎﻣزو ﺪﺳر نﺎﯾﺎﭘ ﻪﺑ ﭗﯾﺎﺗ ﺖﺨﺳ ﻂﯾاﺮﺷ و ﺎﻫرﺎﮑﻫار ﻪﺋارا رد دﻮﺟﻮﻣ یراﻮﺷد و مﺎﻤﺗ ﻪﮐ ﺖﺷاد ﺪﯿﻣا ناﻮﺗ ﯽﻣ ترﻮﺻ ﻦﯾا رد .دﺮﮐ دﺎﺠﯾا ﯽﺳرﺎﻓ نﺎﺑز رد وﺮﺸﯿﭘ ﮓﻨﻫﺮﻓ و ﯽﻗﺎﻠﺧ نﺎﺣاﺮﻃ صﻮﺼﺨﻟا ﯽﻠﻋ یا ﻪﻧﺎﯾار نﺎﺣاﺮﻃ یاﺮﺑ ار یﺮﺘﺸﯿﺑ ﺖﺧﺎﻨﺷ ﺎﻫراﺰﻓا مﺮﻧ ﺎﺑ ﺎﺗ ﺪﺒﻠﻃ ﯽﻣ ار نﺎﺼﺼﺨﺘﻣ و ﻪﻌﻣﺎﺟ ناواﺮﻓ ﺖﺧﺎﻨﺷ هﺪﻨﯾآ و لﺎﺣ ،ﻪﺘﺷﺬﮔ ﺪﺻرد ﻪﺳ و ﺖﺼﺷ رد یدﺎﯾز یﺎﻬﺑﺎﺘﮐ .ﺪﺷﺎﺑ ﯽﻣ یدﺮﺑرﺎﮐ یﺎﻫراﺰﺑا دﻮﺒﻬﺑ فﺪﻫ ﺎﺑ عﻮﻨﺘﻣ یﺎﻫدﺮﺑرﺎﮐ و زﺎﯿﻧ درﻮﻣ یژﻮﻟﻮﻨﮑﺗ ﯽﻠﻌﻓ ﻂﯾاﺮﺷ یاﺮﺑ و ﺖﺳا مزﺎﻟ ﻪﮐ نﺎﻨﭽﻧآﺮﻄﺳ و نﻮﺘﺳ رد ﻪﻠﺠﻣ و ﻪﻣﺎﻧزور ﻪﮑﻠﺑ نﻮﺘﻣ و ﺎﻫﺮﮕﭘﺎﭼ .ﺖﺳا ﮏﯿﻓاﺮﮔ نﺎﺣاﺮﻃ زا هدﺎﻔﺘﺳا ﺎﺑ و پﺎﭼ ﺖﻌﻨﺻ زا مﻮﻬﻔﻣﺎﻧ ﯽﮔدﺎﺳ ﺪﯿﻟﻮﺗ ﺎﺑ ﯽﮕﺘﺧﺎﺳ ﻦﺘﻣ مﻮﺴﭙﯾا مرﻮﻟ\n" +
                            ".دﺮﯿﮔ راﺮﻗ هدﺎﻔﺘﺳا درﻮﻣ ﺎﺳﺎﺳا ﯽﺣاﺮﻃ دﻮﺟﻮﻣ یﺎﯿﻧد ﻞﻫا ﻪﺘﺳﻮﯿﭘ تﺎﻟاﻮﺳ یﻮﮕﺑاﻮﺟ و ﯽﻠﺻا یﺎﻫدروﺎﺘﺳد ﯽﻨﯿﭽﻓوﺮﺣ ﻞﻣﺎﺷ زﺎﯿﻧ درﻮﻣ نﺎﻣزو ﺪﺳر نﺎﯾﺎﭘ ﻪﺑ ﭗﯾﺎﺗ ﺖﺨﺳ ﻂﯾاﺮﺷ و ﺎﻫرﺎﮑﻫار ﻪﺋارا رد دﻮﺟﻮﻣ یراﻮﺷد و مﺎﻤﺗ ﻪﮐ ﺖﺷاد ﺪﯿﻣا ناﻮﺗ ﯽﻣ ترﻮﺻ ﻦﯾا رد .دﺮﮐ دﺎﺠﯾا ﯽﺳرﺎﻓ نﺎﺑز رد وﺮﺸﯿﭘ ﮓﻨﻫﺮﻓ و ﯽﻗﺎﻠﺧ نﺎﺣاﺮﻃ صﻮﺼﺨﻟا ﯽﻠﻋ یا ﻪﻧﺎﯾار نﺎﺣاﺮﻃ یاﺮﺑ ار یﺮﺘﺸﯿﺑ ﺖﺧﺎﻨﺷ ﺎﻫراﺰﻓا مﺮﻧ ﺎﺑ ﺎﺗ ﺪﺒﻠﻃ ﯽﻣ ار نﺎﺼﺼﺨﺘﻣ و ﻪﻌﻣﺎﺟ ناواﺮﻓ ﺖﺧﺎﻨﺷ هﺪﻨﯾآ و لﺎﺣ ،ﻪﺘﺷﺬﮔ ﺪﺻرد ﻪﺳ ",
                    style = TextStyle(color = MaterialTheme.colors.surface),
                    modifier = Modifier,
                )

            }
        }
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Column {
                Text1(
                    modifier = Modifier.padding(15.dp, 10.dp, 15.dp, 0.dp),
                    text = "بیشتر از این دسته",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(color = MaterialTheme.colors.surface)
                )
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(fakeData.size) {
                        var food: Food = fakeData[it]
                        Column(
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp)
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(width = 136.dp, height = 80.dp),
                                shape = RoundedCornerShape(16.dp),
                                elevation = 2.dp
                            ) {
                                Image(
                                    painterResource(R.drawable.pic_food),
                                    contentDescription = "",
                                    contentScale = ContentScale.FillWidth,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Column(
                                modifier = Modifier.padding(
                                    vertical = 10.dp,
                                    horizontal = 10.dp
                                )
                            ) {
                                Text1(
                                    text = "سیب زمینی",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    style = TextStyle(color = MaterialTheme.colors.surface)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text1(
                                    text = "20 دقیقه",
                                    fontSize = 12.sp,
                                    style = TextStyle(color = MaterialTheme.colors.surface)
                                )
                            }

                        }

                    }
                }
            }

        }
    }
}




