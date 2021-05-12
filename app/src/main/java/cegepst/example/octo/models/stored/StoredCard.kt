package cegepst.example.octo.models.stored

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
class StoredCard(
        @PrimaryKey(autoGenerate = false) val id: Long,
        @ColumnInfo(name = "user_id") val userId: Long,
        @ColumnInfo(name = "card_name") val cardName: String,
        @ColumnInfo(name = "quantity") val quantity: Int,
        @ColumnInfo(name = "total") val total: Double
)
