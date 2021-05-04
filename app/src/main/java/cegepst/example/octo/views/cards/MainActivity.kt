package cegepst.example.octo.views.cards

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.octo.R
import cegepst.example.octo.interfaces.IFeedActivity
import cegepst.example.octo.models.helpers.DrawerMenuManager
import cegepst.example.octo.views.FeedActivity
import com.google.android.material.navigation.NavigationView

class MainActivity : FeedActivity(), NavigationView.OnNavigationItemSelectedListener, IFeedActivity {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val callback = { initialize() }
        super.getUser(callback)
    }

    override fun initialize() {
        initializeMenu()
        initializeAdapter()
        setScrollListener()
        fillFeed()
    }

    override fun initializeMenu() {
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

    override fun fillFeed() {
        viewModel.fetchRandomCards()
        viewModel.getCards().observe(this, {
            cards.clear()
            cards.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun initializeAdapter() {
        this.recyclerView = findViewById(R.id.cardList)
        this.recyclerView.adapter = adapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun setScrollListener() {
        this.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    // TODO : load more
                }
            }
        })
    }

    override fun actionLoad() {
        viewModel.fetchRandomCards()
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
