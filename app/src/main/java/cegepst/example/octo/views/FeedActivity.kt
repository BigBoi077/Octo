package cegepst.example.octo.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cegepst.example.octo.R

open class FeedActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
    }
}