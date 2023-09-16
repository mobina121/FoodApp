package com.examplepart.foodpart.network.user
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test3")
data class Entity(
    @PrimaryKey
    val id: String,
    val name: String,
    val infoUrl: String?,
    val image: String?,
)