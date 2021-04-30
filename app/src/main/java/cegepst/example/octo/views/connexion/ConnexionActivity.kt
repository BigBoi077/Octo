package cegepst.example.octo.views.connexion

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import cegepst.example.octo.models.stored.User
import cegepst.example.octo.viewModels.ConnexionViewModel
import cegepst.example.octo.views.BaseActivity
import cegepst.example.octo.views.cards.MainActivity

open class ConnexionActivity : BaseActivity() {

    private lateinit var viewModel: ConnexionViewModel
    internal lateinit var userInputs: HashMap<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel =  ViewModelProvider(this).get(ConnexionViewModel::class.java)
        this.viewModel.initialize(this)
        this.userInputs = HashMap()
    }

    fun logIn() {
        if (isValidForm()) {
            logUser()
        } else {
            alert(viewModel.signUpError)
        }
    }

    fun signUp() {
        if (isValidForm()) {
            registerUser()
        } else {
            alert(viewModel.signUpError)
        }
    }

    private fun logUser() {
        val user = User(
            0,
            "",
            "",
            userInputs["username"]!!,
            userInputs["password"]!!,
            "",
            ""
        )
        val lambda = { log() }
        viewModel.log(user, lambda)
    }

    private fun registerUser() {
        val user = User(
            0,
            userInputs["firstname"]!!,
            userInputs["lastname"]!!,
            userInputs["username"]!!,
            userInputs["password"]!!,
            "",
            ""
        )
        val save = { value: User -> saveUserLogin(value) }
        val proceed = { proceed() }
        viewModel.register(user, save, proceed)

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
