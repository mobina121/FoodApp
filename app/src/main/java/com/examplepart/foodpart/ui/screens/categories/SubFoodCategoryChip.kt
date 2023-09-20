package com.examplepart.foodpart.ui.screens.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.examplepart.foodpart.database.categories.CategoryEntity
import com.examplepart.foodpart.ui.theme.DarkRed
import com.examplepart.foodpart.ui.theme.Transparent


@Composable
fun SubFoodCategoryChip(
    modifier: Modifier,
    subCategoryEntity: CategoryEntity,
    isSelected: Boolean,
    onClicked: () -> Unit
) {
    val borderColor = if (isSelected) MaterialTheme.colors.primary else Transparent
    val backgroundColor =
        if (isSelected) DarkRed else MaterialTheme.colors.surface

    Column(
        modifier = modifier
            .padding(vertical = 3.dp)
            .clip(MaterialTheme.shapes.large)
            .clickable {
                onClicked()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = backgroundColor,
                    shape = MaterialTheme.shapes.large
                )
                .border(
                    width = if (isSelected) 1.dp else 0.dp,
                    color = borderColor,
                    shape = MaterialTheme.shapes.large
                )
                .padding(vertical = 6.dp, horizontal = 20.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = subCategoryEntity.name,
                style = if (isSelected)
                    MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.primary)
                else MaterialTheme.typography.subtitle1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
        }
    }
}

