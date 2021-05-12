package cegepst.example.octo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cegepst.example.octo.models.stored.StoredCard

@Dao
interface WishlistDAO {

    @Query("SELECT * FROM card WHERE id=:id")
    fun getAllCardsByUserId(id: Long): List<StoredCard>

    @Insert
    fun insert(card: StoredCard): Long
}
