package cegepst.example.octo.views.cards

import android.os.Bundle
import cegepst.example.octo.R
import cegepst.example.octo.views.BaseActivity
import cegepst.example.octo.views.FeedActivity

class MainActivity : FeedActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}