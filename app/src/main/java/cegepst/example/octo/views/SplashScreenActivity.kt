package cegepst.example.octo.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import cegepst.example.octo.R
import cegepst.example.octo.views.cards.MainActivity
import cegepst.example.octo.views.connexion.ConnexionActivity
import cegepst.example.octo.views.connexion.LogInActivity
import java.nio.channels.InterruptedByTimeoutException

private const val SPLASH_DELAY = 1250L

@Suppress("DEPRECATION")
class SplashScreenActivity : ConnexionActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        removeActionBar()
        Handler().postDelayed({
            intent = if (userWasLogged()) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, LogInActivity::class.java)
            }
            startActivity(intent)
        }, SPLASH_DELAY)
    }
}
