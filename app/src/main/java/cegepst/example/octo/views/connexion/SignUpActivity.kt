package cegepst.example.octo.views.connexion

import android.os.Bundle
import cegepst.example.octo.R

class SignUpActivity : ConnexionActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        removeActionBar()
    }
}