package com.examplepart.foodpart.ui.screens.food.fullscreenimage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.examplepart.foodpart.R
import com.examplepart.foodpart.ui.common.FoodPartAppBar


@Composable
fun FullscreenImageScreen(
    fullScreenImageScreenViewModel: FullScreenImageScreenViewModel,
    navController: NavController
) {

    val imageId = fullScreenImageScreenViewModel.imageId

    Scaffold(
        topBar = {
            FoodPartAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                title = stringResource(id = R.string.picture),
                showStartIcon = true,
                showEndIcon = true,
                startIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_right),
                            contentDescription = "arrow forward icon",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                },
                endIcon = {
                    IconButton(
                        onClick = {})
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.pic_app_bar),
                            contentDescription = "more icon",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(vertical = 64.dp)
        ) {
            AsyncImage(
                model = imageId,
                contentDescription = imageId,
                error = painterResource(id = R.drawable.logo_dark_1),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
