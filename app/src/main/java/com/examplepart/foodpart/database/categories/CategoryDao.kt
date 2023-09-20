package com.examplepart.foodpart.database.categories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("Select * from categories where isSubCategory = 0")
    fun observeCategories(): Flow<List<CategoryEntity>>

    @Query("Select * from categories where isSubCategory = 1")
    fun observeSubCategories(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoriesOrSubCategories(categoriesOrSubCategories: List<CategoryEntity>)

    @Delete
    suspend fun removeCategory(categoryEntity: CategoryEntity)
}