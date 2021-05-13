package cegepst.example.octo.models.stored

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "firstname") var firstname: String,
    @ColumnInfo(name = "lastname") var lastname: String,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "fav_guild") var favoriteGuild: String,
    @ColumnInfo(name = "fav_color") var favoriteColor: String
)
