package cegepst.example.octo.views.cards

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import cegepst.example.octo.R
import cegepst.example.octo.interfaces.IFeedActivity
import cegepst.example.octo.models.helpers.DrawerMenuManager
import cegepst.example.octo.views.FeedActivity
import com.google.android.material.navigation.NavigationView

class MainActivity : FeedActivity(), NavigationView.OnNavigationItemSelectedListener, IFeedActivity {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val callback = { initialize() }
        super.getUser(callback)
    }

    override fun initialize() {
        initializeMenu()
        fillFeed()
    }

    override fun fillFeed() {
        viewModel.fetchCards()
        viewModel.getCards().observe(this, {
            cards.clear()
            cards.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun initializeMenu() {
        Log.d("CRISS DE MARDE", findViewById<TextView>(R.id.criss).toString())
        drawerLayout = findViewById(R.id.drawer)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.action_open, R.string.action_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        menu = findViewById(R.id.drawerMenu)
        menu.setNavigationItemSelectedListener {
            DrawerMenuManager.handleChosenAction(it, this)
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }
}
