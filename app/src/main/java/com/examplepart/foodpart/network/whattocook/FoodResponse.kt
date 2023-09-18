package com.examplepart.foodpart.network.whattocook

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class FoodResponse (
    val id: String,
    val categoryId: String,
    val name: String,
    val image: String,
    val cookTime: Byte,
    val readyTime: Byte,
)
