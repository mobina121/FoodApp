package ir.partsoftware.programmingquote.database.author

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class FoodEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val infoUrl: String?,
    val image: String?,
)