package cegepst.example.octo.views.connexion

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import cegepst.example.octo.R
import cegepst.example.octo.models.helpers.ColorManager
import cegepst.example.octo.models.helpers.Gravatar
import cegepst.example.octo.models.stored.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputEditText

class PersonalActivity : ConnexionActivity() {

    private lateinit var personalInputs: HashMap<String, TextInputEditText>
    private lateinit var personalColor: ImageView
    private lateinit var gravatar: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal)
        initialize()
        val callback = { user: User -> setContent(user) }
        super.connexionViewModel.getUserById(super.getUserId(), callback, this)
    }

    private fun initialize() {
        this.personalInputs = HashMap()
        personalInputs["firstname"] = getTextInput(R.id.firstNameInput)
        personalInputs["lastname"] = getTextInput(R.id.lastNameInput)
        personalInputs["password"] = getTextInput(R.id.passwordInput)
        personalInputs["email"] = getTextInput(R.id.emailInput)
        personalColor = findViewById(R.id.personalColorChoice)
        gravatar = findViewById(R.id.gravatarProfilePic)
    }

    private fun setContent(user: User) {
        findViewById<TextView>(R.id.username).text = user.username
        personalInputs["firstname"]?.setText(user.firstname)
        personalInputs["lastname"]?.setText(user.lastname)
        personalInputs["email"]?.setText(user.email)
        personalColor.setImageDrawable(
            ColorManager.getAccordingIconForColor(
                user.favoriteColor,
                this
            )
        )
        Glide.with(this).load(Gravatar(user.email).getUrl())
            .apply(RequestOptions.circleCropTransform()).centerCrop().into(gravatar)
    }

    private fun getTextInput(id: Int): TextInputEditText {
        return findViewById(id)
    }
}
