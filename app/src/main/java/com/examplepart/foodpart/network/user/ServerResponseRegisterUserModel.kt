package com.examplepart.foodpart.network.user

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerResponseRegisterUserModel(
    val data: Data
)