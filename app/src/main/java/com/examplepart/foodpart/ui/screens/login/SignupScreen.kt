package com.examplepart.foodpart.ui.screens.login

import androidx.compose.foundation.Image
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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.ui.common.CustomButton
import com.examplepart.foodpart.ui.core.AppScreens


@Composable
fun SignupScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.background,
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(vertical = 10.dp, horizontal = 15.dp)
                            .padding(horizontal = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        IconButton(onClick = {
                            navController.navigate(AppScreens.FoodDetail.route)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_right),
                                contentDescription = "arrow forward icon",
                                tint = MaterialTheme.colors.onBackground
                            )
                        }
                        Spacer(
                            modifier = Modifier.size(10.dp)
                        )
                        Text(
                            modifier = Modifier,
                            text = stringResource(id = R.string.signup),
                            style = MaterialTheme.typography.h2,
                            color = MaterialTheme.colors.onBackground,
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
            SignupScreenContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(25.dp),
                navController = navController
            )
        }
    }
}

@Composable
fun SignupScreenContent(modifier: Modifier, navController: NavController) {
    var userNameText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var repeatPasswordText by remember { mutableStateOf("") }
    Column {

        Box(
            modifier = Modifier.fillMaxWidth(),
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
            style = MaterialTheme.typography.body1,
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
                focusedBorderColor = MaterialTheme.colors.primary,
            ),
            shape = MaterialTheme.shapes.medium,
            placeholder = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    text = stringResource(R.string.userName),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
            },
            textStyle = MaterialTheme.typography.body1,
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
                focusedBorderColor = MaterialTheme.colors.primary,
            ),
            shape = MaterialTheme.shapes.medium,
            placeholder = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    text = stringResource(R.string.password),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
            },
            textStyle = MaterialTheme.typography.body1,
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
                focusedBorderColor = MaterialTheme.colors.primary,
            ),
            shape = MaterialTheme.shapes.medium,
            placeholder = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    text = stringResource(R.string.repeatPassword),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
            },
            textStyle = MaterialTheme.typography.body1,
            maxLines = 2
        )

        CustomButton(modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp), buttonText = stringResource(id = R.string.confirm))
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
                    .clickable {
                        navController.navigate(AppScreens.Login.route)
                    },
                text = stringResource(id = R.string.logIn),
                style = MaterialTheme.typography.caption,
                color = Color(0xff1976D2)
            )

        }
    }
}
