package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SimpleChip(
    modifier: Modifier = Modifier,
    label: String,
    color: Color = MaterialTheme.colors.surface,
    onClick: () -> Unit = {}
) {

    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(80.dp)
            .clip(MaterialTheme.shapes.large)
            .background(color)
            .clickable {
                onClick()
            }
            .padding(vertical = 5.dp, horizontal = 10.dp),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = label,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption
        )
    }
}
