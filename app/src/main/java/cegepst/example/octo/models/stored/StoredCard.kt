package cegepst.example.octo.models.stored

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
class StoredCard (
        @PrimaryKey(autoGenerate = false) val userId: Long,
        @ColumnInfo(name = "card_name") val cardName: String,
        @ColumnInfo(name = "quantity") val quantity: Int
)
