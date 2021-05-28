package cegepst.example.octo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cegepst.example.octo.models.stored.StoredCard
import cegepst.example.octo.models.stored.User

@Dao
interface WishlistDAO {

    @Query("SELECT * FROM card WHERE user_id=:id")
    fun getAllCardsByUserId(id: Long): List<StoredCard>

    @Insert
    fun insert(card: StoredCard): Long

    @Query("DELETE FROM card WHERE user_id=:userId AND id=:cardId")
    fun delete(userId: Long, cardId: Long): Int

    @Update
    fun update(newUser: User)
}
