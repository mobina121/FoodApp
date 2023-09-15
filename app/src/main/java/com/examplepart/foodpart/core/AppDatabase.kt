package com.examplepart.foodpart.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.examplepart.foodpart.database.categories.CategoryDao
import com.examplepart.foodpart.database.categories.CategoryEntity
import com.examplepart.foodpart.database.common.Converter
import com.examplepart.foodpart.database.food.FoodDao
import com.examplepart.foodpart.database.food.FoodEntity

@TypeConverters(Converter::class)
@Database(entities = [CategoryEntity::class, FoodEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao

    abstract fun foodDao(): FoodDao
}