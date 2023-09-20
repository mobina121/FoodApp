package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.examplepart.foodpart.database.food.FoodEntity

@Composable
fun FoodsList(
    items: List<FoodEntity>,
    state: LazyGridState = LazyGridState(),
    onClickFoodItem: (id: String) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(horizontal = 36.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        columns = GridCells.Fixed(2),
        state = state
    ) {
        items(items) { food ->
            FoodItem(
                modifier = Modifier,
                food = food,
            ) {
                onClickFoodItem(food.id)
            }
        }
    }
}