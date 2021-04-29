package cegepst.example.octo.views.connexion

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import cegepst.example.octo.R

class LogInActivity : ConnexionActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        removeActionBar()
    }

    fun goToSignUp(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    fun actionLogIn(view: View) {
        getLogInInputs()
        super.logIn()
    }

    private fun getLogInInputs() {
        userInputs["username"] = getTextInputValue(R.id.usernameInput)
        userInputs["password"] = getTextInputValue(R.id.passwordInput)
    }

    private fun getTextInputValue(id: Int): String {
        return findViewById<EditText>(id).text.toString()
    }
}