package cegepst.example.octo.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
        @PrimaryKey(autoGenerate = true) val id: Long,
        @ColumnInfo(name = "firstname") val firstname: String,
        @ColumnInfo(name = "lastname") val lastname: String,
        @ColumnInfo(name = "username") val username: String,
        @ColumnInfo(name = "password") val password: String,
        @ColumnInfo(name = "fav_guild") val favoriteGuild: String,
        @ColumnInfo(name = "fav_color") val favoriteColor: String
)
