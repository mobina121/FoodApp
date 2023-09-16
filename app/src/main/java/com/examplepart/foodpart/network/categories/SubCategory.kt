package com.examplepart.foodpart.network.categories

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubCategory(
    val id: String,
    val name: String,
    val image: String
)
