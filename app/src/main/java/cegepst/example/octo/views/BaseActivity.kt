package cegepst.example.octo.views

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cegepst.example.octo.models.stored.User
import cegepst.example.octo.stores.AppStore
import cegepst.example.octo.viewModels.BaseViewModel
import cegepst.example.octo.views.connexion.LogInActivity

const val PREF_APP = "app"
const val PREF_USERNAME = "username"
const val PREF_USER_ID = "user_id"
const val PREF_LOGGED = "logged"

open class BaseActivity : AppCompatActivity() {

    internal lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    internal lateinit var user: User
    internal lateinit var viewModel: BaseViewModel
    internal lateinit var database: AppStore
    private lateinit var editor: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.editor = getSharedPreferences(PREF_APP, MODE_PRIVATE)
        this.database = AppStore(this)
        this.viewModel = ViewModelProvider(this).get(BaseViewModel::class.java)
        this.viewModel.initialize(this)
    }

    fun getUser(callback: () -> Unit) {
        val lambda = { user: User -> setUserInformation(user) }
        viewModel.getUser(getUserId(), lambda)
        callback()
    }

    fun removeActionBar() {
        supportActionBar?.hide()
    }

    fun alert(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun userWasLogged(): Boolean {
        return this.editor.getBoolean(PREF_LOGGED, false)
    }

    fun getUsername(): String {
        return this.editor.getString(PREF_USERNAME, "Josh").toString()
    }

    fun disconnectUser() {
        this.editor.edit().clear().apply()
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }

    fun saveUserLogin(user: User) {
        this.user = user
        val editor = getSharedPreferences(PREF_APP, MODE_PRIVATE).edit()
        editor.putString(PREF_USERNAME, user.username)
        editor.putLong(PREF_USER_ID, user.id)
        editor.putBoolean(PREF_LOGGED, true)
        editor.apply()
        editor.commit()
    }

    internal fun getUserId(): Long {
        return this.editor.getLong(PREF_USER_ID, 0)
    }

    private fun setUserInformation(user: User) {
        this.user = user
    }
}
