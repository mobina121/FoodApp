package com.examplepart.foodpart.ui.screens.whatotcook.whatotcook

import android.content.Context
import android.widget.Toast
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
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.platform.LocalContext
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
import com.examplepart.foodpart.network.common.Result
import com.examplepart.foodpart.ui.common.CustomButton
import com.examplepart.foodpart.ui.common.FoodPartAppBar
import com.examplepart.foodpart.ui.theme.Green

@Composable
fun WhatToCookScreen(
    viewModel: WhatToCookViewModel,
    navController: NavController
) {
    val scrollState = rememberScrollState()
    val selectedOption = remember { mutableStateOf("Option1") }
    val (helpMessageVisible, setHelpMessageVisible) = remember { mutableStateOf(true) }


    WhatToCookScreenContent(
        viewModel,
        scrollState,
        selectedOption,
        helpMessageVisible,
        setHelpMessageVisible
    )
    {
        //doSearch
        navController.navigate(AppScreens.WhatToCookResult.route)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun WhatToCookScreenContent(
    viewModel: WhatToCookViewModel,
    scrollState: ScrollState,
    selectedOption: MutableState<String>,
    helpMessageVisible: Boolean,
    setHelpMessageVisible: (Boolean) -> Unit,
    onSearch: () -> Unit
) {
    val whatToCookResult by viewModel.whatToCookResult.collectAsState(Result.Idle)
    val ingredientsError by viewModel.ingredientsValidationState.collectAsState()
    val timeLimitError by viewModel.timeLimitValidationState.collectAsState()

    var showErrorMessage by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf<Int?>(null) }

    var ingredientsText by remember { mutableStateOf("") }
    var timeLimitText by remember { mutableStateOf("") }
    val msg = stringResource(id = R.string.errorMsg)
    val context = LocalContext.current
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

    ) {
        if (whatToCookResult is Result.Loading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }

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
                value = ingredientsText,
                onValueChange = { text -> ingredientsText = text },
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
                value = timeLimitText,
                onValueChange = { text -> timeLimitText = text },
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
            CustomRadioButtonGroup {
                println("Selected option: ${selectedOption.value}")
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

                viewModel.performValidation(ingredientsText, timeLimitText.toIntOrNull())

                if (viewModel.areAllDataParamsValid()) {
                    showErrorMessage = false
                    viewModel.findWhatToCook(ingredientsText, timeLimitText.toInt())
                } else {
                    showErrorMessage = true
                    errorMessage = when {
                        timeLimitError == null -> {
                            showErrorMessage = true
                            R.string.whatToCookTimeLimitError
                        }

                        ingredientsError != null -> {
                            showErrorMessage = true
                            R.string.whatToCookIngredientsError
                        }

                        else -> {
                            showErrorMessage = true
                            null
                        }

                    }
                }
            }
        }
    }

}

private fun showErrorMsg(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
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


@Composable
private fun CustomRadioButtonGroup(
    optionSelected: (selectedOption: String) -> Unit
) {
    var selectedOption by remember { mutableStateOf(Difficulty.NO_MATTER) }

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
                        selected = (selectedOption == option),
                        onClick = {
                            selectedOption = option
                            optionSelected(selectedOption.title)
                        },
                        role = Role.RadioButton
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = if (selectedOption == option) painterResource(id = R.drawable.ic_radio_button_checked) else painterResource(
                        id = R.drawable.ic_radio_button_unchecked
                    ),//R.drawable.ic_report
                    contentDescription = "arrow forward icon",
                    tint = if (selectedOption == option) Green else MaterialTheme.colors.onBackground
                )
                Text(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    text = option.title,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}








