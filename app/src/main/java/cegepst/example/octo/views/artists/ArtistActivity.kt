package cegepst.example.octo.views.artists

import android.os.Bundle
import cegepst.example.octo.R
import cegepst.example.octo.views.FeedActivity

class ArtistActivity : FeedActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)
    }
}
