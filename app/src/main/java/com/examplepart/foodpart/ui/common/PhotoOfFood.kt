package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examplepart.foodpart.ui.core.AppScreens


@Composable
fun PhotoOfFood(navController: NavController, photoId: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 15.dp)
            .height(255.dp)
            .clickable {
                navController.navigate(AppScreens.FullscreenImage.route)
            },

        shape = MaterialTheme.shapes.medium,
        elevation = 0.dp
    ) {
        Image(
            painterResource(photoId),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
        )
    }
}