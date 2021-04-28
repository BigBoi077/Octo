package cegepst.example.octo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cegepst.example.octo.models.User

@Dao
interface UserDAO {

    @Query("SELECT * FROM user WHERE id=:id")
    fun get(id: Long): User

    @Query("SELECT * FROM user WHERE username=:username")
    fun get(username: String): User

    @Update
    fun update(user: User)

    @Insert
    fun insert(user: User): Long

    @Query("DELETE FROM user WHERE id=:id")
    fun delete(id: Long)
}
