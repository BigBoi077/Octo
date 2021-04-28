package cegepst.example.octo.views.connexion

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import cegepst.example.octo.R
import cegepst.example.octo.models.User
import cegepst.example.octo.viewModels.ConnexionViewModel
import cegepst.example.octo.views.BaseActivity
import com.google.android.material.textfield.TextInputEditText

open class ConnexionActivity : BaseActivity() {

    private lateinit var viewModel: ConnexionViewModel
    private lateinit var userInputs: HashMap<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel =  ViewModelProvider(this).get(ConnexionViewModel::class.java)
        this.viewModel.initialize(this)
        this.userInputs = HashMap()
    }

    fun signUp() {
        if (isValidSignUp()) {
            registerUser()
        } else {
            alert(viewModel.signUpError)
        }
    }

    fun getUserInputs() {
        userInputs["firstname"] = getTextInputValue(R.id.firstNameInput)
        userInputs["lastname"] = getTextInputValue(R.id.lastNameInput)
        userInputs["username"] = getTextInputValue(R.id.usernameInput)
        userInputs["password"] = getTextInputValue(R.id.passwordInput)
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
        val lambda = { proceed() }
        viewModel.register(user, lambda)
    }

    private fun proceed() {
        val intent = Intent(this, MoreInformationActivity::class.java)
        startActivity(intent)
    }

    private fun isValidSignUp(): Boolean {
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

    private fun getTextInputValue(id: Int): String {
        return findViewById<TextInputEditText>(id).text.toString()
    }
}
