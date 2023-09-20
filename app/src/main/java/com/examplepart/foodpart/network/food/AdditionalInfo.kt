package com.examplepart.foodpart.network.food

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class AdditionalInfo(
    val difficulty: DifficultyModel,
    val meals: List<MealModel>?,
    val similarFoods: List<String>?,
)





