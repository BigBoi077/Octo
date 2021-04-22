package cegepst.example.octo.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cegepst.example.octo.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun removeActionBar() {
        supportActionBar?.hide();
    }

    fun launchNewActivity() {
        val intent = Intent()
        startActivity(intent)
    }
}