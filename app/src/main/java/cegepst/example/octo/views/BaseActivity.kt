package cegepst.example.octo.views

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import cegepst.example.octo.models.stored.User
import cegepst.example.octo.stores.AppStore
import cegepst.example.octo.viewModels.BaseViewModel

const val PREF_APP = "app"
const val PREF_USERNAME = "username"
const val PREF_USER_ID = "user_id"
const val PREF_LOGGED = "logged"

open class BaseActivity : AppCompatActivity() {

    private lateinit var viewModel: BaseViewModel
    private lateinit var editor: SharedPreferences
    private lateinit var database: AppStore
    internal lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.editor = getSharedPreferences(PREF_APP, MODE_PRIVATE)
        this.database = AppStore(this)
        this.viewModel = ViewModelProvider(this).get(BaseViewModel::class.java)
        this.viewModel.initialize(this)
    }

    fun getUser() {
        val lambda = { user: User -> setUserInformation(user) }
        viewModel.getUser(getUserId(), lambda)
    }

    fun removeActionBar() {
        supportActionBar?.hide();
    }

    fun alert(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun userWasLogged(): Boolean {
        return this.editor.getBoolean(PREF_LOGGED, false)
    }

    private fun setUserInformation(user: User) {
        this.user = user
    }

    private fun getUserId(): Long {
        return this.editor.getLong(PREF_USER_ID, 0)
    }

    fun getUsername(): String {
        return this.editor.getString(PREF_USERNAME, "Josh").toString()
    }

    fun saveUserLogin(user: User) {
        this.user = user
        val editor = getSharedPreferences(PREF_APP, MODE_PRIVATE).edit()
        editor.putString(PREF_USERNAME, user.username)
        editor.putLong(PREF_USER_ID, user.id)
        editor.putBoolean(PREF_LOGGED, false)
        editor.apply()
        editor.commit()
    }
}
