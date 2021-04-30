package cegepst.example.octo.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.ViewModelProvider
import cegepst.example.octo.R
import cegepst.example.octo.interfaces.IFeedActivity
import cegepst.example.octo.models.base.Card
import cegepst.example.octo.models.helpers.DrawerMenuManager
import cegepst.example.octo.viewModels.ConnexionViewModel
import cegepst.example.octo.viewModels.FeedViewModel
import cegepst.example.octo.views.adapters.CardAdapter
import com.google.android.material.navigation.NavigationView

open class FeedActivity : BaseActivity() {

    internal lateinit var menu: NavigationView
    internal lateinit var viewModel: FeedViewModel
    internal lateinit var cards: ArrayList<Card>
    internal lateinit var adapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize() {
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
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
            else -> super.onOptionsItemSelected(item)
        }
    }
}
