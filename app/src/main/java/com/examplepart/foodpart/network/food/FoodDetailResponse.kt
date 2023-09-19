package com.examplepart.foodpart.network.food

import com.examplepart.foodpart.database.food.FoodEntity
import com.examplepart.foodpart.network.whattocook.FoodResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class FoodDetailResponse(
    @Json(name = "data")
    val foodInfo: FoodResponse,
    val additionalInfo: AdditionalInfo,
)