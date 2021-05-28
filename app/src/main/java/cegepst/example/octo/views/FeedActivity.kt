package cegepst.example.octo.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cegepst.example.octo.R
import cegepst.example.octo.models.base.Card
import cegepst.example.octo.models.helpers.DrawerMenuManager
import cegepst.example.octo.viewModels.FeedViewModel
import cegepst.example.octo.views.adapters.CardAdapter
import cegepst.example.octo.views.connexion.PersonalActivity
import com.google.android.material.navigation.NavigationView

open class FeedActivity : BaseActivity() {

    internal lateinit var menu: NavigationView
    internal lateinit var feedViewModel: FeedViewModel
    internal lateinit var cards: ArrayList<Card>
    internal lateinit var adapter: CardAdapter
    internal lateinit var drawerLayout: DrawerLayout
    internal lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize() {
        feedViewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        cards = ArrayList()
        adapter = CardAdapter(cards)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_log_out -> {
                super.disconnectUser()
                true
            }
            R.id.action_personal_account -> {
                val intent = Intent(this, PersonalActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun initializeMenu(activity: FeedActivity) {
        drawerLayout = activity.findViewById(R.id.drawer)
        actionBarDrawerToggle = ActionBarDrawerToggle(activity, drawerLayout, R.string.action_open, R.string.action_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        menu = activity.findViewById(R.id.drawerMenu)
        menu.setNavigationItemSelectedListener {
            DrawerMenuManager.handleChosenAction(it, activity)
            true
        }
        supportActionBar?.title = ""
    }

    fun initializeAdapter(activity: BaseActivity, id: Int) {
        this.recyclerView = activity.findViewById(id)
        this.recyclerView.adapter = adapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun setScrollListener(callback: () -> Unit) {
        this.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    callback()
                }
            }
        })
    }
}
