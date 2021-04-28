package cegepst.example.octo.views.connexion

import android.os.Bundle
import cegepst.example.octo.R
import cegepst.example.octo.models.User
import cegepst.example.octo.models.UserManager
import cegepst.example.octo.views.BaseActivity
import com.google.android.material.textfield.TextInputEditText

open class ConnexionActivity : BaseActivity() {

    internal lateinit var currentError: String
    private lateinit var signUpError: String
    private lateinit var usernameTakenError: String
    private lateinit var userManager: UserManager
    private lateinit var userInputs: HashMap<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.signUpError = getString(R.string.signUpError)
        this.usernameTakenError = getString(R.string.usernameTakenError)
        this.currentError = ""
        this.userManager = UserManager(super.database)
        this.userInputs = HashMap()
    }

    fun getUserInputs() {
        userInputs["firstname"] = getTextInputValue(R.id.firstNameInput)
        userInputs["lastname"] = getTextInputValue(R.id.lastNameInput)
        userInputs["username"] = getTextInputValue(R.id.usernameInput)
        userInputs["password"] = getTextInputValue(R.id.passwordInput)
    }

    fun userWasLogged(): Boolean {
        // TODO : use app preferences to check user logged
        return false    
    }

    fun isValidSignUp(): Boolean {
        if (isUsernameTaken(userInputs["username"].toString())) {
            this.currentError = usernameTakenError
            return false
        }
        if (!fieldsCorrect()) {
            this.currentError = signUpError
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

    private fun isUsernameTaken(username: String): Boolean {
        return userManager.userExist(username)
    }

    private fun getTextInputValue(id: Int): String {
        return findViewById<TextInputEditText>(id).text.toString()
    }

    fun registerUser() {
    }
}