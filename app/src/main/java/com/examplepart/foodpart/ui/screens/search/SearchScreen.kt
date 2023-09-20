package com.examplepart.foodpart.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.core.AppScreens
import com.examplepart.foodpart.datamodel.fakeData
import com.examplepart.foodpart.ui.common.FoodPartAppBar

@Composable
fun SearchScreen(navController: NavController) {

    SearchScreenContent(
        onCancelSearch = {},
        ocClickFood = {
            navController.navigate(AppScreens.FoodDetail.route)
        }
    )
}

@Composable
private fun SearchScreenContent(
    onCancelSearch: () -> Unit,
    ocClickFood: () -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    val scrollState = rememberLazyGridState()
    val foods = fakeData
    var isFound by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            FoodPartAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 0.dp),
                title = stringResource(id = R.string.whatAreYouLookingFor),
                showStartIcon = false,
                showEndIcon = false,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),

            ) {

            val trailingIconView = @Composable {
                if (searchText.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            searchText = ""
                            isFound = true
                        },
                    ) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "",
                            tint = if (isFound) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary
                        )
                    }
                }
            }
            OutlinedTextField(
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                value = searchText,
                onValueChange = { text -> searchText = text },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = MaterialTheme.colors.surface,
                    unfocusedBorderColor = if (isFound) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary,
                    focusedBorderColor = if (isFound) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary,
                    cursorColor = MaterialTheme.colors.onSurface
                ),
                shape = MaterialTheme.shapes.medium,
                placeholder = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                            text = stringResource(R.string.wrightHereInformal),
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.subtitle1,
                            color = if (isFound) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                },
                textStyle = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                trailingIcon = trailingIconView
            )
            if (searchText.isNotEmpty()) {
                if (searchText == "موز") {
                    isFound = false
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.nothingFound),
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                } else {
                    isFound = true
                    Column {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp, 0.dp, 20.dp, 10.dp),
                            text = "${stringResource(id = R.string.resultFor)} $searchText",
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onBackground
                        )
                        LazyVerticalGrid(
                            modifier = Modifier.padding(horizontal = 36.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            columns = GridCells.Fixed(2),
                            state = scrollState,
                        ) {
                            items(foods) { food ->
//                                FoodItem(
//                                    modifier = Modifier,
//                                    food = food,
//                                ) {
//                                    ocClickFood()
//                                }
                            }
                        }
                    }
                }
            }
        }
    }


}