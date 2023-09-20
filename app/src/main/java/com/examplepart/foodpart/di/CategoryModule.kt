package com.examplepart.foodpart.di

import com.examplepart.foodpart.core.AppDatabase
import com.examplepart.foodpart.database.categories.CategoryDao
import com.examplepart.foodpart.network.categories.CategoriesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CategoryModule {
    @Provides
    fun provideCategoryApi(retrofit: Retrofit): CategoriesApi {
        return retrofit.create(CategoriesApi::class.java)
    }

    @Provides
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao {
        return appDatabase.categoryDao()
    }
}