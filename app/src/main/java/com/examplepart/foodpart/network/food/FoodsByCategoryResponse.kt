package com.examplepart.foodpart.network.food

import com.examplepart.foodpart.network.whattocook.FoodResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class FoodsByCategoryResponse(
    @Json(name = "data")
    val foods: List<FoodResponse>,
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
)