package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FoodPartAppBar(
    modifier: Modifier = Modifier,
    title: String,
    showStartIcon: Boolean = true,
    showEndIcon: Boolean = true,
    startIcon: @Composable (() -> Unit)? = null,
    endIcon: @Composable (() -> Unit)? = null,
    contentModifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 4.dp,
    ) {
        Row(
            modifier = contentModifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (showStartIcon) {
                startIcon?.invoke()
            }

            Text(
                modifier = Modifier.weight(1f),
                text = title,
                color = MaterialTheme.colors.onBackground,
                style = if (showStartIcon) MaterialTheme.typography.h2 else MaterialTheme.typography.h1,
            )

            if (showEndIcon) {
                endIcon?.invoke()
            }
        }
    }
}