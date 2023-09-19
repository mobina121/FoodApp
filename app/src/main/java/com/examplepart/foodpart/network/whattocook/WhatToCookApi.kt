package com.examplepart.foodpart.network.whattocook

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WhatToCookApi {

    @GET("v1/what-to-cook/")
    suspend fun whatToCook(

        @Query("ingredients") ingredients: String,
        @Query("timeLimit") timeLimit: Int,
        @Query("difficulty") difficulty: Int?,
    ): Response<WhatToCookResponse>
}