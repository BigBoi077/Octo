package cegepst.example.octo.stores

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cegepst.example.octo.dao.UserDAO
import cegepst.example.octo.dao.WishlistDAO
import cegepst.example.octo.models.stored.StoredCard
import cegepst.example.octo.models.stored.User

@Database(entities = [User::class, StoredCard::class], version = 1)
abstract class AppStore : RoomDatabase() {

    abstract fun userDAO(): UserDAO
    abstract fun wishListDAO(): WishlistDAO

    companion object {
        @Volatile
        private var instance: AppStore? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
                context,
                AppStore::class.java,
                "Octo-4"
        ).build()
    }
}
    