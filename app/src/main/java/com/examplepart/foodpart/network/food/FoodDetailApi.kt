package com.examplepart.foodpart.network.food

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodDetailApi {
    @GET("v1/food/")
    suspend fun getFoodDetail(
        @Query("ingredients") foodId: String,

    ): Response<FoodDetailResponse>
}