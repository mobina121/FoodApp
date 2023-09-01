package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun CustomChip(
    icon: Int?,
    label: String,
    hasColor: Boolean,
    color: Color?,
    modifier: Modifier?
) {
    val backgroundColor = if (hasColor && color != null) {
        color
    } else MaterialTheme.colors.surface

    Row(
        modifier = Modifier
            .fillMaxHeight()
            .clip(MaterialTheme.shapes.large)
            .background(backgroundColor)
            .padding(vertical = 5.dp, horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (icon != null)
            Image(
                painterResource(icon),
                modifier = Modifier
                    .padding(0.dp, 0.dp, 10.dp, 0.dp)
                    .size(18.dp),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
            )

        Text(
            text = label,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption
        )

    }
}
