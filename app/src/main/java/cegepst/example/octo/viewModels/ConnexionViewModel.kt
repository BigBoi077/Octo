package cegepst.example.octo.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import cegepst.example.octo.R
import cegepst.example.octo.models.User
import cegepst.example.octo.stores.AppStore
import cegepst.example.octo.views.BaseActivity
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConnexionViewModel: ViewModel() {

    private lateinit var database: AppStore
    private lateinit var activity: BaseActivity

    lateinit var signUpError: String
    lateinit var usernameTakenError: String
    lateinit var logInError: String

    fun initialize(activity: BaseActivity) {
        this.activity = activity
        this.database = AppStore(activity)
        this.signUpError = activity.getString(R.string.sign_up_error)
        this.usernameTakenError = activity.getString(R.string.username_taken_error)
        this.logInError = activity.getString(R.string.log_in_error)
    }

    fun update(user: User, lambda: () -> Unit) {
        GlobalScope.launch {
            database.userDAO().update(user)
            withContext(Dispatchers.Main) {
                lambda()
            }
        }
    }

    fun register(user: User, save: (User) -> Unit, proceed: () -> Unit) {
        database.userDAO().getByUsernameObserve(user.username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    activity.alert(usernameTakenError)
                },
                onError = {
                    insert(user)
                    getSave(user, save, proceed)
                }
            )
    }

    private fun insert(user: User) {
        GlobalScope.launch {
            database.userDAO().insert(user)
        }
    }

    private fun getSave(user: User, save: (User) -> Unit, proceed: () -> Unit) {
        GlobalScope.launch {
            val freshUser = database.userDAO().getByUsername(user.username)
            Log.d("FRESH USER", Gson().toJson(freshUser))
            withContext(Dispatchers.Main) {
                save(freshUser)
                proceed()
            }
        }
    }

    fun log(user: User, lambda: () -> Unit) {
        database.userDAO().getByUsernamePassword(user.username, user.password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    activity.alert(logInError)
                },
                onError = {
                    lambda()
                }
            )
    }
}
