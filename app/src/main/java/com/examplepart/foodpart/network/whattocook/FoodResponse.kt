package com.examplepart.foodpart.network.whattocook

import com.examplepart.foodpart.database.food.FoodEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class FoodResponse(
    val id: String,
    val categoryId: String,
    val name: String,
    val image: String,
    val cookTime: Int?,
    val readyTime: Int?,
    val count: String?,
    val point: String?,
    val difficulty: String?,
    val location: String?,
    val recipe: String?,
    val ingredients: String?,
    val meals: List<String>?,
    val similarFoods: List<String>?
) {
    fun toFoodEntity() = FoodEntity(
        id = id,
        categoryId = categoryId,
        name = name,
        image = image,
        cookTime = cookTime,
        readyTime = readyTime,
        count = count,
        point = point,
        difficulty = difficulty,
        recipe = recipe,
        ingredients = ingredients,
        meals = meals,
        similarFoods = similarFoods,
    )
}
