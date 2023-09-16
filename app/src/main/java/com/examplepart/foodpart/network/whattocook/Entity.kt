package com.examplepart.foodpart.network.whattocook
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test4")
data class Entity(
    @PrimaryKey
    val id: String,
    val name: String,
    val infoUrl: String?,
    val image: String?,
)