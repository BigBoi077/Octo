package cegepst.example.octo.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.RoomDatabase
import cegepst.example.octo.R
import cegepst.example.octo.stores.AppStore

open class BaseActivity : AppCompatActivity() {

    protected lateinit var database: AppStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        this.database = AppStore(this)
    }

    fun removeActionBar() {
        supportActionBar?.hide();
    }

    fun alert(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
