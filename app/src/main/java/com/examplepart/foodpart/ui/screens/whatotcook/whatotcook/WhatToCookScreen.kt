package com.examplepart.foodpart.ui.screens.whatotcook.whatotcook

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.core.AppScreens
import com.examplepart.foodpart.core.Difficulty
import com.examplepart.foodpart.ui.common.CustomButton
import com.examplepart.foodpart.ui.common.FoodPartAppBar
import com.examplepart.foodpart.ui.theme.Green

@Composable
fun WhatToCookScreen(
    whatToCookViewModel: WhatToCookViewModel,
    navController: NavController
) {
    val scrollState = rememberScrollState()
    val (helpMessageVisible, setHelpMessageVisible) = remember { mutableStateOf(true) }


    WhatToCookScreenContent(
        whatToCookViewModel,
        scrollState,
        helpMessageVisible,
        setHelpMessageVisible
    )
    { ingredients, timeLimit, difficulty ->

        navController.navigate(
            AppScreens.WhatToCookResult
                .createRoute(
                    ingredients = ingredients,
                    timeLimit = timeLimit,
                    difficulty = difficulty
                )
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun WhatToCookScreenContent(
    whatToCookViewModel: WhatToCookViewModel,
    scrollState: ScrollState,
    helpMessageVisible: Boolean,
    setHelpMessageVisible: (Boolean) -> Unit,
    onSearchClicked: (ingredients: String, timeLimit: String, difficulty: String?) -> Unit
) {
    val ingredientsError by whatToCookViewModel.ingredientsValidationState.collectAsState()

    var showErrorMessage by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf<Int?>(null) }


    val ingredients = whatToCookViewModel.ingredients.collectAsState()
    val timeLimit = whatToCookViewModel.timeLimit.collectAsState()
    val difficultyTitle = whatToCookViewModel.difficultyTitle.collectAsState()


    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            FoodPartAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 0.dp),
                title = stringResource(id = R.string.whatToCook),
                showStartIcon = false,
                showEndIcon = false,
            )
        },
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    backgroundColor = Color.DarkGray,
                    contentColor = MaterialTheme.colors.onBackground,
                    snackbarData = data
                )
            }
        }

    ) { it ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),

            ) {

            if (helpMessageVisible) {
                HelpMessage(onCloseClick = {
                    setHelpMessageVisible(false)
                })
            }

            OutlinedTextField(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                value = ingredients.value,
                onValueChange = {
                    whatToCookViewModel.updateIngredients(it)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = MaterialTheme.colors.surface,
                    unfocusedBorderColor = MaterialTheme.colors.surface,
                    focusedBorderColor = MaterialTheme.colors.onSurface,
                    cursorColor = MaterialTheme.colors.onSurface,
                ),
                shape = MaterialTheme.shapes.medium,
                placeholder = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        text = stringResource(R.string.whatDoYouHave),
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.onSurface
                    )
                },
                textStyle = MaterialTheme.typography.subtitle1,
                maxLines = 2
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                text = stringResource(R.string.helpOne),
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSurface
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth()
                    .height(56.dp),
                value = timeLimit.value,
                onValueChange = {
                    whatToCookViewModel.updateTimeLimit(it)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = MaterialTheme.colors.surface,
                    unfocusedBorderColor = MaterialTheme.colors.surface,
                    focusedBorderColor = MaterialTheme.colors.onSurface,
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
                            modifier = Modifier,
                            text = stringResource(id = R.string.howMuchTimeDoYouHave),
                            color = MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.subtitle1
                        )
                        Text(
                            modifier = Modifier,
                            text = stringResource(id = R.string.time),
                            color = MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                },
                textStyle = MaterialTheme.typography.subtitle1,
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                visualTransformation = VisualTransformation.None,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                text = stringResource(R.string.howDifficultIsTheOrder),
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onSurface
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Difficulty.values().forEach { option ->
                    Row(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.medium)
                            .selectable(
                                selected = (difficultyTitle.value == option),
                                onClick = {
                                    whatToCookViewModel.updateDifficulty(option)
                                },
                                role = Role.RadioButton
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = if (difficultyTitle.value == option) painterResource(id = R.drawable.ic_radio_button_checked) else painterResource(
                                id = R.drawable.ic_radio_button_unchecked
                            ),
                            contentDescription = "arrow forward icon",
                            tint = if (difficultyTitle.value == option) Green else MaterialTheme.colors.onBackground
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 5.dp),
                            text = option.title,
                            style = MaterialTheme.typography.caption
                        )
                    }
                }
            }

            if (showErrorMessage) {
                errorMessage?.let { messageResId ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp, horizontal = 16.dp),
                        text = stringResource(id = messageResId),
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))


            CustomButton(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                buttonText = stringResource(id = R.string.search),
            ) {


                whatToCookViewModel.performValidation()


                if (whatToCookViewModel.areAllDataParamsValid()) {
                    showErrorMessage = false
                    onSearchClicked(ingredients.value, timeLimit.value, difficultyTitle.value.title)
                } else {
                    showErrorMessage = true
                    errorMessage = when {
                        ingredientsError != null -> {
                            showErrorMessage = true
                            R.string.whatToCookIngredientsError
                        }

                        else -> {
                            showErrorMessage = true
                            R.string.whatToCookTimeLimitError
                        }

                    }
                }
            }
        }
    }

}

@Composable
private fun HelpMessage(
    onCloseClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colors.surface)
            .padding(vertical = 20.dp, horizontal = 16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.help),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.subtitle1
            )
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onCloseClick()
                    },
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "arrow forward icon",
                tint = MaterialTheme.colors.onBackground
            )
        }
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(id = R.string.helpText),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.subtitle1
        )
    }
}