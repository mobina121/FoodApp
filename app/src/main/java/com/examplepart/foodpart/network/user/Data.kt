package com.examplepart.foodpart.network.user

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val avatar: String,
    val id: String,
    val username: String,
)