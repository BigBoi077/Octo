package cegepst.example.octo.views.cards

import android.os.Bundle
import android.view.MenuItem
import cegepst.example.octo.R
import cegepst.example.octo.models.base.Card
import cegepst.example.octo.views.FeedActivity
import cegepst.example.octo.views.fragments.SingleCardFragment
import com.google.android.material.navigation.NavigationView

class SingleCardActivity : FeedActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_card)
        val callback = { initialize() }
        super.getUser(callback)
    }

    fun initialize() {
        this.initializeMenu()
        this.getSingleCard()
    }

    private fun getSingleCard() {
        val lambda = { card: Card -> initializeFragment(card) }
        feedViewModel.fetchSingleCard(intent.getStringExtra("cardId"), lambda)
    }

    private fun initializeFragment(card: Card) {
        val alert = { message: String -> super.alert(message) }
        supportActionBar?.title = ""
        supportFragmentManager.beginTransaction()
                .add(
                        R.id.singleCardContainer,
                        SingleCardFragment.newInstance(card, super.viewModel, super.user, alert)
                )
                .commit()
    }

    private fun initializeMenu() {
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
}