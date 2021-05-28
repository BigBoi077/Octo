package cegepst.example.octo.views.cards

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import cegepst.example.octo.R
import cegepst.example.octo.interfaces.IFeedActivity
import cegepst.example.octo.views.FeedActivity
import com.google.android.material.navigation.NavigationView

class CommanderActivity : FeedActivity(), NavigationView.OnNavigationItemSelectedListener, IFeedActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.artistShowcase).height = 0
        val callback = { initialize() }
        super.getUser(callback)
    }

    override fun initialize() {
        this.initializeMenu()
        this.initializeAdapter()
        this.setScrollListener()
        this.fillFeed()
    }

    override fun fillFeed() {
        feedViewModel.fetchCommanderCards()
        feedViewModel.getCards().observe(this, {
            cards.clear()
            cards.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun initializeMenu() {
        super.initializeMenu(this)
    }

    override fun setScrollListener() {
        val callback = { actionLoad() }
        super.setScrollListener(callback)
    }

    override fun initializeAdapter() {
        super.initializeAdapter(this, R.id.cardList)
    }

    override fun actionLoad() {
        feedViewModel.fetchCommanderCards()
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