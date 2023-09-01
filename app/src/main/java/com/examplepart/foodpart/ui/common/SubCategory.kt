package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.examplepart.foodpart.R


@Composable
fun SubCategory(icon: Int, label: String, modifier: Modifier) {
    val backgroundColor = when (label) {
        stringResource(id = R.string.easy) -> Color(0xA000FF67).copy(alpha = 0.2f)
        stringResource(id = R.string.difficult) -> Color(0xA0FF6262).copy(alpha = 0.2f)
        stringResource(id = R.string.normal) -> Color(0xA0FFE100).copy(alpha = 0.2f)
        else -> MaterialTheme.colors.surface
    }

    val color = when (label) {
        stringResource(id = R.string.easy) -> Color(0xff00FF67)
        stringResource(id = R.string.difficult) -> Color(0xffFF6262)
        else -> Color(0xffFFE100)
    }
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .clip(MaterialTheme.shapes.large)
            .background(
                color = backgroundColor
            )
            .border(
                width = 1.dp,
                color = color,
                shape = MaterialTheme.shapes.large
            )
            .padding(vertical = 5.dp, horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier
                .padding(0.dp, 0.dp, 10.dp, 0.dp)
                .size(18.dp),
            painter = painterResource(id = icon),
            contentDescription = "",
            tint = color
        )

        Text(
            text = label,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption
        )
    }
}