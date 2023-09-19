package com.examplepart.foodpart.di

import com.examplepart.foodpart.network.food.FoodDetailApi
import com.examplepart.foodpart.network.user.UserApi
import com.examplepart.foodpart.network.whattocook.WhatToCookApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object FoodDetailModule {

    @Provides
    fun provideFoodDetailApi(retrofit: Retrofit): FoodDetailApi {
        return retrofit.create(FoodDetailApi::class.java)
    }
}