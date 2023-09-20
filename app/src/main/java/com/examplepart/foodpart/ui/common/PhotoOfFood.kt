package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.examplepart.foodpart.R


@Composable
fun PhotoOfFood(
    photoId: String,
    onClickPhoto: (photoId: String) -> Unit

) {
    AsyncImage(
        model = photoId,
        contentDescription = photoId,
        error = painterResource(id = R.drawable.logo_dark_1),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(244.dp)
            .padding(horizontal = 16.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onClickPhoto(photoId)
            }
    )
}