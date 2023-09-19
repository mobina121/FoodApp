package com.examplepart.foodpart.network.food

import com.examplepart.foodpart.database.food.DifficultyEntity
import com.examplepart.foodpart.database.food.FoodEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DifficultyModel(
    @Json(name = "id")
    val difficultyId: String,
    @Json(name = "name")
    val difficultyLevel: String,
) {
    fun toDifficultyEntity() = DifficultyEntity(
        difficultyId = difficultyId,
        difficultyLevel = difficultyLevel
    )
}
