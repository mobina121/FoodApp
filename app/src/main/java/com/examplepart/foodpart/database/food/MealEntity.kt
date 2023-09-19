package com.examplepart.foodpart.database.food

import androidx.room.Entity

@Entity(tableName = "meal")
data class MealEntity(
    val mealId: String,
    val meal: String
)