//package com.examplepart.foodpart.network.categories
//
//import retrofit2.Response
//import retrofit2.http.GET
//import retrofit2.http.Query
//
//interface CategoriesApi {
//
//    @GET("v1/category")
//    suspend fun getCategories(
//        @Query("count") count: Int? = null // 100
//    ): Response<List<AuthorResponse>>
//
//}