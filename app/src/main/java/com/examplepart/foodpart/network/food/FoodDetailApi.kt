package com.examplepart.foodpart.network.food

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
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
    ): Response<SimilarFoodsResponse>

    @GET("v1/food")
    suspend fun getFoodsByCategory(
        @Query("category") category: String,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): Response<FoodsByCategoryResponse>

    @POST("v1/food/{id}/report")
    suspend fun sendReportFood(
        @Path("id") foodId: String,
        @Header("Authorization") authorization: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb2xsZWN0aW9uSWQiOiJfcGJfdXNlcnNfYXV0aF8iLCJleHAiOjE2OTY0MzgxNTksImlkIjoieDk3a2FmaXBpc2R5ZDJxIiwidHlwZSI6ImF1dGhSZWNvcmQifQ.ckL46J-XjVA2RDAPqJ-Z-9RqbSoXlAoo8mYfOTPjc_c",
        @Body body: SendReportFoodModel
    ): Response<String>

    @GET("v1/food")
    suspend fun getFoodsByMeal(
        @Query("meal") mealId: String
    ): Response<SimilarFoodsResponse>

}