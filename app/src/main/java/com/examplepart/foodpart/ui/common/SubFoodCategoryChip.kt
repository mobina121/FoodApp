package com.examplepart.foodpart.ui.common

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.examplepart.foodpart.datamodel.SubFoodCategoryModel


@Composable
fun SubFoodCategoryChip(
    subFoodCategoryModel: SubFoodCategoryModel,
    isSelected: Boolean,
    onClicked: () -> Unit
) {
    val borderColor = if (isSelected) MaterialTheme.colors.primary else Color(0x00000000)
    val backgroundColor =
        if (isSelected) Color(0xA0FF6262).copy(alpha = 0.2f) else MaterialTheme.colors.surface

    Column(
        modifier = Modifier
            .padding(4.dp)
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
                .padding(vertical = 10.dp, horizontal = 20.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = subFoodCategoryModel.name,
                style = if (isSelected)
                    MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.primary)
                else MaterialTheme.typography.subtitle1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
        }
    }
}

