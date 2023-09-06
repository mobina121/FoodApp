package com.examplepart.foodpart.datamodel

data class SubFoodCategoryModel(
    val id: Int,
    val subCategoryName: String,
    val sunCategoryAvatarUrl: String,
    val sunCategoryImageUrl: String,
    val foods: List<FoodItemModel> = emptyList()
)