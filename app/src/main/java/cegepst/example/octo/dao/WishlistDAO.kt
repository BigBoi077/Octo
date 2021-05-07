package cegepst.example.octo.dao

import androidx.room.Dao
import androidx.room.Insert
import cegepst.example.octo.models.stored.StoredCard

@Dao
interface WishlistDAO {

    @Insert
    fun insert(card: StoredCard): Long
}
