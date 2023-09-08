package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.Image
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
import com.examplepart.foodpart.R


@Composable
fun PhotoOfFood(
    photoId: String,
    onClickPhoto: (photoId: String) -> Unit

) {
    Image(
        painter = painterResource(R.drawable.pic_food), //photo Id
        contentDescription = "",
        contentScale = ContentScale.FillWidth,
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