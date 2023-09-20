package com.examplepart.foodpart.network.food

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    @GET("v1/food")
    suspend fun getFoodsByCategoryId(
        @Query("category") category: String,
        @Query("page") page: Int? = null,
        @Query("perPage") perPage: Int? = null
    ): Response<Data>
}