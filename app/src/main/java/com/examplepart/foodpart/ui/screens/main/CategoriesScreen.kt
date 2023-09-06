package com.examplepart.foodpart.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.examplepart.foodpart.R
import com.examplepart.foodpart.datamodel.foodCategories
import com.examplepart.foodpart.ui.common.FoodCategoryChip
import com.examplepart.foodpart.ui.common.FoodPartAppBar
import com.examplepart.foodpart.ui.common.SubFoodCategoryChip


@Composable
fun CategoriesScreen() {
    val foodCategories = foodCategories
    var selectedCategoryIndex by remember { mutableStateOf<Int?>(null) }
    var selectedSubCategoryIndex by remember { mutableStateOf<Int?>(null) }


    Column(modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp)) {
        FoodPartAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            title = stringResource(id = R.string.foodPart),
            showStartIcon = false,
            showEndIcon = false,
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(foodCategories) { index, foodCategoryModel ->
                FoodCategoryChip(
                    foodCategoryModel = foodCategoryModel,
                    isSelected = selectedCategoryIndex == index
                ) {
                    selectedCategoryIndex = index
                }
            }
        }

        Divider(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .height(0.5.dp),
            color = MaterialTheme.colors.onSurface
        )

        val selectedCategory = selectedCategoryIndex?.let { foodCategories.getOrNull(it) }
        val hasSubcategories = selectedCategory?.subCategories?.isNotEmpty() == true

        selectedCategory?.let { category ->
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(category.subCategories) { index, subCategoryModel ->
                    SubFoodCategoryChip(
                        subFoodCategoryModel = subCategoryModel,
                        isSelected = selectedSubCategoryIndex == index
                    ) {
                        selectedSubCategoryIndex = index
                    }
                }
            }
        }
        if (hasSubcategories) {
            Divider(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .height(0.5.dp),
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}