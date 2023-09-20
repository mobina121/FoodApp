package com.examplepart.foodpart.database.food

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class FoodEntity(
    @PrimaryKey
    val id: String,
    val categoryId: String,
    val name: String,
    val image: String,
    val cookTime: Int?,
    val readyTime: Int?,
    val count: String?,
    val point: String?,
    val difficulty: String?,
    val location: String?,
    val recipe: String?,
    val ingredients: String?,
    val meals: List<String>? = null,
    val similarFoods: List<String>? = null
)