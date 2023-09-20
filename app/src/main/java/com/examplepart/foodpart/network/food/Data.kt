package com.examplepart.foodpart.network.food

import com.squareup.moshi.JsonClass
import ir.partsoftware.programmingquote.database.author.FoodResponse

@JsonClass(generateAdapter = true)
data class Data(
    val data: List<FoodResponse>
)
