package cegepst.example.octo.views.cards

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import cegepst.example.octo.R
import cegepst.example.octo.interfaces.IFeedActivity
import cegepst.example.octo.views.FeedActivity
import com.google.android.material.navigation.NavigationView

class MainActivity : FeedActivity(), NavigationView.OnNavigationItemSelectedListener, IFeedActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val callback = { initialize() }
        super.getUser(callback)
    }

    override fun initialize() {
        this.initializeMenu()
        this.initializeAdapter()
        this.setScrollListener()
        this.fillFeed()
    }

    override fun initializeMenu() {
        super.initializeMenu(this)
    }

    override fun fillFeed() {
        val callback = { name: String -> setArtistTitle(name) }
        feedViewModel.fetchRandomCards(callback)
        feedViewModel.getCards().observe(this, {
            cards.clear()
            cards.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun initializeAdapter() {
        super.initializeAdapter(this)
    }

    override fun setScrollListener() {}

    override fun actionLoad() {
        feedViewModel.fetchRandomCards()
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

    @SuppressLint("SetTextI18n")
    private fun setArtistTitle(artistName: String) {
        val title = findViewById<TextView>(R.id.artistShowcase)
        title.text = "Today's chosen artist is : $artistName"
    }
}
