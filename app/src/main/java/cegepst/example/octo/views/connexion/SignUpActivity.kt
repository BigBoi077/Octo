package cegepst.example.octo.views.connexion

import android.os.Bundle
import android.view.View
import cegepst.example.octo.R
import com.google.android.material.textfield.TextInputEditText

class SignUpActivity : ConnexionActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        removeActionBar()
    }

    fun actionSignUp(view: View) {
        getSignUpInputs()
        super.signUp()
    }

    private fun getSignUpInputs() {
        userInputs["firstname"] = getTextInputValue(R.id.firstNameInput)
        userInputs["lastname"] = getTextInputValue(R.id.lastNameInput)
        userInputs["username"] = getTextInputValue(R.id.usernameInput)
        userInputs["password"] = getTextInputValue(R.id.passwordInput)
        userInputs["email"] = getTextInputValue(R.id.emailInput)
    }

    private fun getTextInputValue(id: Int): String {
        return findViewById<TextInputEditText>(id).text.toString()
    }
}
