package cegepst.example.octo.views.artists

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import cegepst.example.octo.R
import cegepst.example.octo.interfaces.IFeedActivity
import cegepst.example.octo.models.base.ArtPiece
import cegepst.example.octo.models.base.ArtistShowcase
import cegepst.example.octo.models.helpers.WebScraper
import cegepst.example.octo.views.FeedActivity
import cegepst.example.octo.views.adapters.SingleArtistAdapter
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView

class SingleArtistActivity : FeedActivity(), NavigationView.OnNavigationItemSelectedListener,
    IFeedActivity {

    private lateinit var artistShowcase: ArtistShowcase
    private lateinit var singleArtistAdapter: SingleArtistAdapter
    private lateinit var artPieces: List<ArtPiece>
    private lateinit var artistName: String
    private lateinit var artistUrl: String
    private lateinit var webScraper: WebScraper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_artist)
        this.artistName = intent.getStringExtra("artistName")!!
        this.artistUrl = intent.getStringExtra("artistUrl")!!
        this.initialize()
    }

    override fun initialize() {
        this.initializeMenu()
        this.fillFeed()
    }

    override fun fillFeed() {
        val callback = { artistShowcase: ArtistShowcase -> setArtistShowcase(artistShowcase) }
        this.artPieces = listOf()
        this.webScraper = WebScraper(this.artistUrl)
        webScraper.findArtistInformation(this.artistName, callback)
    }

    private fun setArtistShowcase(artistShowcase: ArtistShowcase) {
        this.artistShowcase = artistShowcase
        this.singleArtistAdapter = SingleArtistAdapter(this.artistShowcase.artPieces)
        this.recyclerView = findViewById(R.id.artPieces)
        this.recyclerView.adapter = singleArtistAdapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)
        findViewById<TextView>(R.id.artistName).text = artistName
        Glide.with(this).load(artistShowcase.artistImageUrl).centerCrop()
            .into(findViewById(R.id.artistProfilePic))
        findViewById<ImageView>(R.id.artistProfilePic).setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(this.artistUrl))
            startActivity(browserIntent)
        }
    }

    override fun initializeMenu() {
        super.initializeMenu(this)
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

    override fun setScrollListener() {}

    override fun initializeAdapter() {}

    override fun actionLoad() {}
}