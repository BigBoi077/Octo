package cegepst.example.octo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cegepst.example.octo.models.stored.User
import io.reactivex.Single

@Dao
interface UserDAO {

    @Query("SELECT * FROM user WHERE id=:id")
    fun getById(id: Long): User

    @Query("SELECT * FROM user WHERE username=:username")
    fun getByUsernameObserve(username: String): Single<User>

    @Query("SELECT * FROM user WHERE username=:username")
    fun getByUsername(username: String): User

    @Query("SELECT * FROM user WHERE username=:username AND password=:password")
    fun getByUsernamePassword(username: String, password: String): Single<User>

    @Query("DELETE FROM user WHERE id=:id")
    fun delete(id: Long)

    @Update
    fun update(user: User)

    @Insert
    fun insert(user: User): Long
}
