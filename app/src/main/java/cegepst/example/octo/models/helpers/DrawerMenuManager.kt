package cegepst.example.octo.models.helpers

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import cegepst.example.octo.R
import cegepst.example.octo.views.artists.ArtistActivity
import cegepst.example.octo.views.cards.CommanderActivity
import cegepst.example.octo.views.cards.MainActivity
import cegepst.example.octo.views.cards.WishListActivity

class DrawerMenuManager {

    companion object {
        fun handleChosenAction(menuItem: MenuItem, activity: Activity) {
            when (menuItem.itemId) {
                R.id.actionRandomCards -> {
                    val intent = Intent(activity, MainActivity::class.java)
                    activity.startActivity(intent)
                }
                R.id.actionCommanderCards -> {
                    val intent = Intent(activity, CommanderActivity::class.java)
                    activity.startActivity(intent)
                }
                R.id.actionArtists -> {
                    val intent = Intent(activity, ArtistActivity::class.java)
                    activity.startActivity(intent)
                }
                R.id.actionPersonalWishList -> {
                    val intent = Intent(activity, WishListActivity::class.java)
                    activity.startActivity(intent)
                }
            }
        }
    }
}