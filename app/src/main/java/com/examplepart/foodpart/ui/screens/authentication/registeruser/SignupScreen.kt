package com.examplepart.foodpart.ui.screens.authentication.registeruser

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.ui.common.CustomButton
import com.examplepart.foodpart.ui.common.FoodPartAppBar
import com.examplepart.foodpart.core.AppScreens


@Composable
fun SignupScreen(navController: NavController) {
    Scaffold(
        topBar = {
            FoodPartAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 0.dp),
                title = stringResource(id = R.string.signup),
                showStartIcon = true,
                showEndIcon = false,
                startIcon = {

                    IconButton(onClick = {
                        navController.navigate(AppScreens.FoodDetail.route)
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
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            SignupScreenContent(
                navController = navController
            ) {
                //doSignup
            }
        }
    }
}

@Composable
fun SignupScreenContent(
    navController: NavController,
    onSignup: () -> Unit
) {
    var userNameText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var repeatPasswordText by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(R.drawable.icon_round),
                modifier = Modifier.padding(bottom = 50.dp),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = stringResource(id = R.string.welcome),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onBackground
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 20.dp),
            text = stringResource(id = R.string.interYourInfoForSingUp),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onBackground
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(20.dp, 30.dp, 20.dp, 0.dp)
                .fillMaxWidth()
                .height(55.dp),
            value = userNameText,
            onValueChange = { text -> userNameText = text },
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
                        .padding(horizontal = 20.dp),
                    text = stringResource(R.string.userName),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface
                )
            },
            textStyle = MaterialTheme.typography.subtitle1,
            maxLines = 2
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(20.dp, 10.dp, 20.dp, 0.dp)
                .fillMaxWidth()
                .height(55.dp),
            value = passwordText,
            onValueChange = { text -> passwordText = text },
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
                        .padding(horizontal = 20.dp),
                    text = stringResource(R.string.password),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface
                )
            },
            textStyle = MaterialTheme.typography.subtitle1,
            maxLines = 2
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(20.dp, 10.dp, 20.dp, 0.dp)
                .fillMaxWidth()
                .height(55.dp),
            value = repeatPasswordText,
            onValueChange = { text -> repeatPasswordText = text },
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
                        .padding(horizontal = 20.dp),
                    text = stringResource(R.string.repeatPassword),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface
                )
            },
            textStyle = MaterialTheme.typography.subtitle1,
            maxLines = 2
        )

        CustomButton(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
            buttonText = stringResource(id = R.string.confirm)
        ) {
            onSignup()
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.areUSingOutBefore),
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onBackground
            )

            Text(
                modifier = Modifier
                    .padding(end = 30.dp, start = 4.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .clickable {
                        navController.navigate(AppScreens.Login.route)
                    },
                text = stringResource(id = R.string.logIn),
                style = MaterialTheme.typography.caption,
                color = Blue
            )
        }
    }
}
