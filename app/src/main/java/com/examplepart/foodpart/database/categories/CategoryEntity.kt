package com.examplepart.foodpart.database.categories

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val image: String,
    val subCategories: List<String>?
)