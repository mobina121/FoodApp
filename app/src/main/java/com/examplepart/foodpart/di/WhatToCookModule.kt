package com.examplepart.foodpart.di

import com.examplepart.foodpart.network.user.UserApi
import com.examplepart.foodpart.network.whattocook.WhatToCookApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object WhatToCookModule {

    @Provides
    fun provideWhatToCookApi(retrofit: Retrofit): WhatToCookApi {
        return retrofit.create(WhatToCookApi::class.java)
    }
}