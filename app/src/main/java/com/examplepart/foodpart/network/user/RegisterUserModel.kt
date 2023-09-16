package com.examplepart.foodpart.network.user

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterUserModel(
    val username: String,
    val password: String,
)