package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.examplepart.foodpart.datamodel.FoodItemModel


@Composable
fun DisplayItemsForSubCategory(
    items: List<FoodItemModel>,
    onClickFoodItem: (id: String) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(horizontal = 36.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(items) { food ->
            FoodItem(
                food = food,
            ) {
                onClickFoodItem(food.id)
            }
        }
    }
}
