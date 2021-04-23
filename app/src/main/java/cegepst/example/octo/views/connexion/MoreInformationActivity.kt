package cegepst.example.octo.views.connexion

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import cegepst.example.octo.R


class MoreInformationActivity : ConnexionActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_information)
        removeActionBar()
        populateSpinner()
    }

    private fun populateSpinner() {
        val guilds = ArrayList<String>()
        guilds.addAll(resources.getStringArray(R.array.guilds))
        val spinner = findViewById<Spinner>(R.id.guildSpinner)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item, guilds
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    fun onRadioButtonClicked(view: View) {}
    fun actionRegister(view: View) {}
}
