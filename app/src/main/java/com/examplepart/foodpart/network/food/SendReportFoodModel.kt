package com.examplepart.foodpart.network.food

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SendReportFoodModel(
    val description: String,
)