package cegepst.example.octo.views.connexion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cegepst.example.octo.R

class LogInActivity : ConnexionActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        removeActionBar()
    }
}