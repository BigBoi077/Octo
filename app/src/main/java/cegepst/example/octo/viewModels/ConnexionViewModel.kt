package cegepst.example.octo.viewModels

import androidx.lifecycle.ViewModel
import cegepst.example.octo.R
import cegepst.example.octo.models.User
import cegepst.example.octo.stores.AppStore
import cegepst.example.octo.views.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConnexionViewModel(): ViewModel() {

    private lateinit var database: AppStore
    private lateinit var activity: BaseActivity

    lateinit var signUpError: String
    lateinit var usernameTakenError: String

    fun initialize(activity: BaseActivity) {
        this.activity = activity
        this.signUpError = activity.getString(R.string.signUpError)
        this.usernameTakenError = activity.getString(R.string.usernameTakenError)
    }

    fun getUser(userId: Long, lambda: (User) -> Unit) {
        GlobalScope.launch {
            val user = database.userDAO().getById(userId)
            withContext(Dispatchers.Main) {
                lambda(user)
            }
        }
    }

    fun update(user: User) {
        GlobalScope.launch {
            database.userDAO().update(user)
            withContext(Dispatchers.Main) {
                return@withContext
            }
        }
    }

    fun register(user: User, proceed: () -> Unit) {
        database.userDAO().getByUsername(user.username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    activity.alert(usernameTakenError)
                },
                onError = {
                    insert(user)
                    proceed()
                }
            )
    }

    private fun insert(user: User) {
        GlobalScope.launch {
            database.userDAO().insert(user)
            withContext(Dispatchers.Main) {
                return@withContext
            }
        }
    }
}
