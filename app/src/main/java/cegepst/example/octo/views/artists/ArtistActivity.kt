package cegepst.example.octo.views.artists

import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import cegepst.example.octo.R
import cegepst.example.octo.interfaces.IFeedActivity
import cegepst.example.octo.models.base.Artist
import cegepst.example.octo.models.helpers.WebScraper
import cegepst.example.octo.views.FeedActivity
import cegepst.example.octo.views.adapters.ArtistAdapter
import com.google.android.material.navigation.NavigationView

private const val ARTIST_URL = "https://www.originalmagicart.store"

class ArtistActivity : FeedActivity(), NavigationView.OnNavigationItemSelectedListener, IFeedActivity {

    private lateinit var artistAdapter: ArtistAdapter
    private lateinit var artists: List<Artist>
    private lateinit var webScraper: WebScraper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)
        this.initialize()
    }

    override fun initialize() {
        this.initializeMenu()
        this.fillFeed()
    }

    override fun initializeMenu() {
        super.initializeMenu(this)
    }

    override fun fillFeed() {
        val callback = { artists: List<Artist> -> setArtistInformation(artists) }
        this.artists = listOf()
        this.webScraper = WebScraper(ARTIST_URL)
        webScraper.findArtists(callback)
    }

    override fun initializeAdapter() {}

    override fun setScrollListener() {}

    override fun actionLoad() {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

    private fun setArtistInformation(artists: List<Artist>) {
        this.artists = artists
        this.artistAdapter = ArtistAdapter(this.artists)
        this.recyclerView = findViewById(R.id.artistList)
        this.recyclerView.adapter = artistAdapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
