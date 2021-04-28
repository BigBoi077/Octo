package cegepst.example.octo.views.connexion

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import cegepst.example.octo.R

private const val COLORLESS = "Colorless"
private const val RED = "Red"
private const val BLUE = "Blue"
private const val BLACK = "Black"
private const val WHITE = "White"
private const val GREEN = "Green"

class MoreInformationActivity : ConnexionActivity() {

    private lateinit var guildSpinner: Spinner
    private lateinit var currentChosenColor: String
    private lateinit var currentChosenGuild: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.getUser()
        this.currentChosenColor = COLORLESS
        setContentView(R.layout.activity_more_information)
        removeActionBar()
        populateSpinner()
    }

    private fun populateSpinner() {
        val guilds = ArrayList<String>()
        guilds.addAll(resources.getStringArray(R.array.guilds))
        guildSpinner = findViewById(R.id.guildSpinner)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item, guilds
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        guildSpinner.adapter = adapter
    }

    fun onRadioButtonClicked(view: View) {
        when (view.id) {
            R.id.colorless_mana -> currentChosenColor = COLORLESS
            R.id.red_mana -> currentChosenColor = RED
            R.id.blue_mana -> currentChosenColor = BLUE
            R.id.black_mana -> currentChosenColor = BLACK
            R.id.white_mana -> currentChosenColor = WHITE
            R.id.green_mana -> currentChosenColor = GREEN
        }
    }

    fun actionRegister(view: View) {
        val chosenGuild = guildSpinner.selectedItem

    }
}
