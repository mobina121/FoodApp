package com.examplepart.foodpart.database.food

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.examplepart.foodpart.database.categories.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoFoods(foods: List<FoodEntity>)

    @Query("Select * from foods where categoryId=:id")
    fun getFoodsByCategoryId(id: String): Flow<List<FoodEntity>>

    @Query("Select * from foods")
    fun getFoods(): Flow<List<FoodEntity>>
}