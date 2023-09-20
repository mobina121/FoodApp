import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test")
data class Entity(
    @PrimaryKey
    val id: String,
    val name: String,
    val infoUrl: String?,
    val image: String?,
)