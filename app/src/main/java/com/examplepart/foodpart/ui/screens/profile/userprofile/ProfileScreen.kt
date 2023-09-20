package com.examplepart.foodpart.ui.screens.profile.userprofile

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.core.AppScreens
import com.examplepart.foodpart.database.food.FoodEntity
import com.examplepart.foodpart.ui.common.CustomButton
import com.examplepart.foodpart.ui.common.FoodItem
import com.examplepart.foodpart.ui.common.FoodPartAppBar

@Composable
fun ProfileScreen(navController: NavController) {
    val favoriteFoods = listOf<FoodEntity>()
    ProfileScreenContent(
        favoriteFoods,
        isUserLSignUp = true,
        goSingUp = {
            navController.navigate(AppScreens.Login.route)
        },
        loggingOut = {
            //loggingOut
            navController.navigate(AppScreens.Login.route)
        }
    )
}

@Composable
private fun ProfileScreenContent(
    foodsList: List<FoodEntity>,
    isUserLSignUp: Boolean,
    goSingUp: () -> Unit,
    loggingOut: () -> Unit
) {

    Scaffold(
        topBar = {
            FoodPartAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 0.dp),
                title = stringResource(id = R.string.profile),
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
        Column(modifier = Modifier.padding(it)) {
            InformationProfile(
                isUserLSignUp,
                onEnter = {
                    goSingUp()
                },
            )
            if (isUserLSignUp) {
                SettingProfile(
                    foodsList,
                    onConfirmation = {}
                )
            }

        }
    }
}

@Composable
private fun InformationProfile(
    isUserLSignUp: Boolean,
    onEnter: () -> Unit,
) {
    var showDialogState by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Image(
            painterResource(R.drawable.pic_profile), //food.imageUrl
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
        )

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            text = if (isUserLSignUp) "2-Z-Warriors" else stringResource(R.string.guest),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h3
        )
        if (isUserLSignUp) {
            Icon(
                modifier = Modifier.clickable {
                    showDialogState = true
                },
                painter = painterResource(id = R.drawable.ic_logout),
                contentDescription = "arrow forward icon",
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
    if (!isUserLSignUp) {
        CustomButton(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
            buttonText = stringResource(id = R.string.enter),
        ) {
            onEnter()
        }
    }
    CustomAlertDialog(showDialogState,
        onDismiss = {
            showDialogState = false
        },
        onExitClicked = {
            showDialogState = false
        },
        onCancelClicked = {
            showDialogState = false
        }
    )
}

@Composable
fun CustomAlertDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onExitClicked: () -> Unit,
    onCancelClicked: () -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Box(
                modifier = Modifier
                    .size(300.dp, 200.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.surface)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        text = stringResource(id = R.string.doYouWantToExit),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.subtitle1
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                onExitClicked()
                                onDismiss()
                            },
                            modifier = Modifier
                                .weight(2.5f)
                                .clip(MaterialTheme.shapes.medium)
                                .background(MaterialTheme.colors.primary),
                            shape = MaterialTheme.shapes.medium

                        ) {
                            Text(
                                text = stringResource(id = R.string.exit),
                                style = MaterialTheme.typography.button
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = {
                                onExitClicked()
                                onDismiss()
                            },
                            modifier = Modifier
                                .weight(1.5f)
                                .clip(MaterialTheme.shapes.medium)
                                .background(MaterialTheme.colors.secondary),
                            shape = MaterialTheme.shapes.medium

                        ) {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                style = MaterialTheme.typography.button
                            )
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun SettingProfile(
    foodsList: List<FoodEntity>,
    onConfirmation: () -> Unit
) {
    val scrollState = rememberScrollState()
    var nweUserNameText by remember { mutableStateOf("") }
    var currentPassText by remember { mutableStateOf("") }
    var newPassText by remember { mutableStateOf("") }
    var repeatNewPasText by remember { mutableStateOf("") }
    val isPasswordMatching by derivedStateOf {
        newPassText == repeatNewPasText
    }
    var showDropDownChangeName by remember { mutableStateOf(false) }
    var showDropDownChangePassword by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),

        ) {

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(foodsList) { index, food ->
                val startPadding = if (index == 0) 16.dp else 0.dp
                val endPadding = if (index == foodsList.size - 1) 16.dp else 0.dp
                FoodItem(
                    modifier = Modifier.padding(start = startPadding, end = endPadding),
                    food
                ) {}
            }
        }


        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .clip(MaterialTheme.shapes.medium)
                .clickable {
                    showDropDownChangeName = !showDropDownChangeName
                }
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = stringResource(id = R.string.changeUserName),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onBackground
            )
            Icon(
                modifier = Modifier.size(24.dp),
                painter = if (showDropDownChangeName) painterResource(id = R.drawable.arrow_left) else painterResource(
                    id = R.drawable.ic_arrow_back
                ),
                contentDescription = "arrow forward icon",
                tint = MaterialTheme.colors.onBackground
            )
        }

        AnimatedVisibility(
            visible = showDropDownChangeName,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(vertical = 12.dp, horizontal = 24.dp)
                        .fillMaxWidth()
                        .height(56.dp),
                    value = nweUserNameText,
                    onValueChange = { text -> nweUserNameText = text },
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
                                .fillMaxWidth(),
                            text = stringResource(R.string.newUserName),
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.onSurface
                        )
                    },
                    textStyle = MaterialTheme.typography.subtitle1,
                    maxLines = 2
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .clip(MaterialTheme.shapes.medium)
                .clickable {
                    showDropDownChangePassword = !showDropDownChangePassword
                }
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = stringResource(id = R.string.changePassword),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onBackground
            )
            Icon(
                modifier = Modifier.size(24.dp),
                painter = if (showDropDownChangePassword) painterResource(id = R.drawable.arrow_left) else painterResource(
                    id = R.drawable.ic_arrow_back
                ),
                contentDescription = "arrow forward icon",
                tint = MaterialTheme.colors.onBackground
            )
        }

        AnimatedVisibility(
            visible = showDropDownChangePassword,
        ) {
            Box {
                Column {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 24.dp)
                            .fillMaxWidth()
                            .height(56.dp),
                        value = currentPassText,
                        onValueChange = { text -> currentPassText = text },
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
                                    .fillMaxWidth(),
                                text = stringResource(R.string.currentPassword),
                                style = MaterialTheme.typography.subtitle1,
                                color = MaterialTheme.colors.onSurface
                            )
                        },
                        textStyle = MaterialTheme.typography.subtitle1,
                        maxLines = 2
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth()
                            .height(56.dp),
                        value = newPassText,
                        onValueChange = { text -> newPassText = text },
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
                                    .fillMaxWidth(),
                                text = stringResource(R.string.newPassword),
                                style = MaterialTheme.typography.subtitle1,
                                color = MaterialTheme.colors.onSurface
                            )
                        },
                        textStyle = MaterialTheme.typography.subtitle1,
                        maxLines = 2
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 24.dp)
                            .fillMaxWidth()
                            .height(56.dp),
                        value = repeatNewPasText,
                        onValueChange = { text -> repeatNewPasText = text },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = MaterialTheme.colors.surface,
                            unfocusedBorderColor = MaterialTheme.colors.surface,
                            focusedBorderColor = if (isPasswordMatching) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary,
                            cursorColor = if (isPasswordMatching) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary
                        ),
                        shape = MaterialTheme.shapes.medium,
                        placeholder = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = stringResource(R.string.repeatNewPassword),
                                style = MaterialTheme.typography.subtitle1,
                                color = MaterialTheme.colors.onSurface
                            )
                        },
                        textStyle = MaterialTheme.typography.subtitle1,
                        maxLines = 2
                    )
                }

            }
        }
        Spacer(modifier = Modifier.weight(1f))
        CustomButton(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
            buttonText = stringResource(id = R.string.onConfirmation),
            enable = (nweUserNameText != "") || (currentPassText != "") || (newPassText != "")
        ) {
            onConfirmation()
        }
    }
}
