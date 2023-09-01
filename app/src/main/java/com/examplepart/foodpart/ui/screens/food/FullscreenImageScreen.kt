package com.examplepart.foodpart.ui.screens.food

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.ui.common.FoodAppBar
import com.examplepart.foodpart.ui.core.AppScreens


@Composable
fun FullscreenImageScreen(navController: NavController) {
    Scaffold(
        topBar = {
            FoodAppBar(
                modifier = Modifier.padding(horizontal = 15.dp),
                title = {
                    Text(
                        text = stringResource(R.string.picture),
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onBackground,
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.pic_app_bar),
                            contentDescription = "more icon",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(AppScreens.FoodDetail.route)

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
            Image(
                painter = painterResource(id = R.drawable.pic_food),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.padding(paddingValues).fillMaxSize()
            )
        }

    }
}