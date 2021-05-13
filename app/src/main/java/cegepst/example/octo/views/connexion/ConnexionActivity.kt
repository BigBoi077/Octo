package cegepst.example.octo.views.connexion

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import cegepst.example.octo.models.stored.User
import cegepst.example.octo.viewModels.ConnexionViewModel
import cegepst.example.octo.views.BaseActivity
import cegepst.example.octo.views.cards.MainActivity

open class ConnexionActivity : BaseActivity() {

    internal lateinit var connexionViewModel: ConnexionViewModel
    internal lateinit var userInputs: HashMap<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.connexionViewModel = ViewModelProvider(this).get(ConnexionViewModel::class.java)
        this.connexionViewModel.initialize(this)
        this.userInputs = HashMap()
    }

    fun logIn() {
        if (isValidForm()) {
            logUser()
        } else {
            alert(connexionViewModel.signUpError)
        }
    }

    fun signUp() {
        if (isValidForm()) {
            registerUser()
        } else {
            alert(connexionViewModel.signUpError)
        }
    }

    private fun logUser() {
        val user = User(
            0,
            "",
            "",
            userInputs["username"]!!,
            "",
            userInputs["password"]!!,
            "",
            ""
        )
        val saveUser = { user: User -> saveUserLogin(user) }
        val logUser = { log() }
        connexionViewModel.log(user, saveUser, logUser)
    }

    private fun registerUser() {
        val user = User(
            0,
            userInputs["firstname"]!!,
            userInputs["lastname"]!!,
            userInputs["username"]!!,
            userInputs["email"]!!,
            userInputs["password"]!!,
            "",
            ""
        )
        val save = { value: User -> saveUserLogin(value) }
        val proceed = { proceed() }
        connexionViewModel.register(user, save, proceed)

    }

    private fun log() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun proceed() {
        val intent = Intent(this, MoreInformationActivity::class.java)
        startActivity(intent)
    }

    private fun isValidForm(): Boolean {
        if (!fieldsCorrect()) {
            return false
        }
        return true
    }

    private fun fieldsCorrect(): Boolean {
        for (field in userInputs.values) {
            if (field.isEmpty()) {
                return false
            }
        }
        return true
    }
}
