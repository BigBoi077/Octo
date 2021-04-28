package cegepst.example.octo.models

import androidx.room.RoomDatabase
import cegepst.example.octo.stores.AppStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserManager(private val database: AppStore) {

    private var userExist: Boolean = false

    fun launchAsync(lambdaFirst: () -> Any, lambdaSecond: () -> Unit) {
        GlobalScope.launch {
            lambdaFirst()
            withContext(Dispatchers.Main) {
                lambdaSecond()
            }
        }
    }

    fun userExist(username: String): Boolean {
        val lambdaFirst = {

        }
        val lambdaSecond = {}
        return this.userExist
    }
}
