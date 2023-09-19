package com.examplepart.foodpart.database.food
import androidx.room.Entity

@Entity(tableName = "difficulty")
data class DifficultyEntity(
    val difficultyId: String,
    val difficultyLevel: String
)