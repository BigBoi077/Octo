package cegepst.example.octo.views.connexion

import android.os.Bundle
import android.view.View
import cegepst.example.octo.R

class SignUpActivity : ConnexionActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        removeActionBar()
    }

    fun actionSignUp(view: View) {
        super.getUserInputs()
        super.signUp()
    }
}
