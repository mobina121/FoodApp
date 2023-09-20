package com.examplepart.foodpart.network.food

import com.examplepart.foodpart.database.food.MealEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealModel(
    @Json(name = "id")
    val mealId: String,
    @Json(name = "name")
    val meal: String,
) {
    fun toMealEntity() = MealEntity(
        mealId = mealId,
        meal = meal
    )
}
