package com.examplepart.foodpart.network.categories

import retrofit2.Response
import retrofit2.http.GET

interface CategoriesApi {

    @GET("v1/category")
    suspend fun getCategories(): Response<Data>

}