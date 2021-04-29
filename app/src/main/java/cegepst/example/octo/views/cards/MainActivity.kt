package cegepst.example.octo.views.cards

import android.os.Bundle
import cegepst.example.octo.R
import cegepst.example.octo.views.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test()
    }
}