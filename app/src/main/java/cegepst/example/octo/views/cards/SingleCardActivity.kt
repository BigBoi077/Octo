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
        viewModel.fetchSingleCard(intent.getStringExtra("cardId"), lambda)
    }

    private fun initializeFragment(card: Card) {
        val lambda = { card: Card,
                       totalPrice: Double,
                       quantity: Int ->
            super.viewModel.insertCard(user.id, card, totalPrice, quantity)
        }
        supportActionBar?.title = ""
        supportFragmentManager.beginTransaction()
                .add(
                        R.id.singleCardContainer,
                        SingleCardFragment.newInstance(card, lambda)
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