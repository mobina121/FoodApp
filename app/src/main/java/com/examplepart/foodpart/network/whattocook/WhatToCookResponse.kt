package com.examplepart.foodpart.network.whattocook

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WhatToCookResponse(
    val data: List<FoodResponse>
)

