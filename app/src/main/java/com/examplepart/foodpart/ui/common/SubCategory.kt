package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.examplepart.foodpart.R
import com.examplepart.foodpart.ui.theme.DarkGreen
import com.examplepart.foodpart.ui.theme.DarkRed
import com.examplepart.foodpart.ui.theme.DarkYellow
import com.examplepart.foodpart.ui.theme.Green
import com.examplepart.foodpart.ui.theme.Yellow


@Composable
fun SubCategory(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val backgroundColor = when (label) {
        stringResource(id = R.string.easy) -> DarkGreen
        stringResource(id = R.string.difficult) -> DarkRed
        stringResource(id = R.string.normal) -> DarkYellow
        else -> MaterialTheme.colors.surface
    }

    val borderColor = when (label) {
        stringResource(id = R.string.easy) -> Green
        stringResource(id = R.string.difficult) -> MaterialTheme.colors.primary
        else -> Yellow
    }

    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(
                color = backgroundColor
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = MaterialTheme.shapes.large
            )
            .clickable {
                onClick()
            }
            .padding(vertical = 5.dp, horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier
                .padding(0.dp, 0.dp, 10.dp, 0.dp)
                .size(18.dp),
            painter = painterResource(id = R.drawable.ic_leaf),
            contentDescription = "",
            tint = borderColor
        )

        Text(
            text = label,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption
        )
    }
}