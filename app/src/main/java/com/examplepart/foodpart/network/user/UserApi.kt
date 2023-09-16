package com.examplepart.foodpart.network.user

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("v1/user/register/")
    suspend fun createUser(
        @Body body: RegisterUserModel
    ): Response<ServerResponseRegisterUserModel>
}