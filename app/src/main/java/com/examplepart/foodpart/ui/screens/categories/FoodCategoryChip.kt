package com.examplepart.foodpart.ui.screens.categories

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.examplepart.foodpart.R
import com.examplepart.foodpart.database.categories.CategoryEntity
import com.examplepart.foodpart.ui.theme.DarkRed


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodCategoryChip(
    modifier: Modifier,
    categoryEntity: CategoryEntity,
    isSelected: Boolean,
    onClicked: () -> Unit
) {
    val borderColor = if (isSelected) MaterialTheme.colors.primary else Color.Gray
    val backgroundColor =
        if (isSelected) DarkRed else Color.Transparent

    Column(
        modifier = modifier
            .padding(4.dp)
            .width(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(
                    color = backgroundColor,
                    shape = MaterialTheme.shapes.medium
                )
                .border(
                    width = if (isSelected) 1.dp else 0.dp,
                    color = borderColor,
                    shape = MaterialTheme.shapes.medium
                )

        ) {
            AsyncImage(
                model = categoryEntity.image,
                contentDescription = categoryEntity.name,
                error = painterResource(id = R.drawable.salectes_cat),
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .align(Alignment.Center)
                    .clickable {
                        onClicked()
                    }
            )
        }

        Text(
            modifier = Modifier
                .basicMarquee()
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            text = categoryEntity.name,
            color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.subtitle1,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}