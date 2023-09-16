package com.examplepart.foodpart.network.categories

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val data: List<CategoryResponse>
)
