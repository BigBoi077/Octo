package cegepst.example.octo.views.cards

import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import cegepst.example.octo.R
import cegepst.example.octo.models.stored.StoredCard
import cegepst.example.octo.views.FeedActivity
import cegepst.example.octo.views.adapters.WishListAdapter
import com.google.android.material.navigation.NavigationView

class WishListActivity : FeedActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var wishListAdapter: WishListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wish_list)
        val callback = { initialize() }
        super.getUser(callback)
    }

    fun initialize() {
        this.initializeMenu(this)
        this.fillFeed()
    }

    private fun fillFeed() {
        val lambda = { cards: List<StoredCard> -> initializeAdapter(cards) }
        super.feedViewModel.getWishList(super.getUserId(), lambda, this)
    }

    private fun initializeAdapter(cards: List<StoredCard>) {
        val alert = { message: String -> super.alert(message) }
        this.wishListAdapter = WishListAdapter(cards, alert)
        this.recyclerView = findViewById(R.id.cardList)
        this.recyclerView.adapter = this.wishListAdapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)
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