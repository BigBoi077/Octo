package cegepst.example.octo.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import cegepst.example.octo.R
import cegepst.example.octo.interfaces.IFeedActivity

open class FeedActivity : BaseActivity(), IFeedActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun initialize() {}

    override fun fillFeed() {}
}
