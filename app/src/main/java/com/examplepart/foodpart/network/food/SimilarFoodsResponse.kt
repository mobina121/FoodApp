package com.examplepart.foodpart.network.food

import com.examplepart.foodpart.network.whattocook.FoodResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class SimilarFoodsResponse(
    @Json(name = "data")
    val similarFoods: List<FoodResponse>,
)