package com.examplepart.foodpart.network.food

import com.examplepart.foodpart.network.whattocook.FoodResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodDetailApi {
    @GET("v1/food/{id}")
    suspend fun getFoodDetail(
        @Path("id") foodId: String,
        ): Response<FoodDetailResponse>


    @GET("v1/food")
    suspend fun getFoodsByIds(
        @Query("ids") ids: String
    ): Response<List<FoodResponse>>

}