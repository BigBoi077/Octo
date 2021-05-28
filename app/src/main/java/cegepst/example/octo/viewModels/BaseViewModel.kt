package cegepst.example.octo.viewModels

import androidx.lifecycle.ViewModel
import cegepst.example.octo.models.base.Card
import cegepst.example.octo.models.stored.StoredCard
import cegepst.example.octo.models.stored.User
import cegepst.example.octo.services.ScryfallService
import cegepst.example.octo.stores.AppStore
import cegepst.example.octo.views.BaseActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

open class BaseViewModel: ViewModel() {

    internal lateinit var database: AppStore
    private lateinit var activity: BaseActivity

    internal val scryfallService by lazy {
        ScryfallService.create()
    }

    fun initialize(activity: BaseActivity) {
        this.activity = activity
        this.database = AppStore(activity)
    }

    fun getUser(userId: Long, lambda: (User) -> Unit) {
        GlobalScope.launch {
            val user = database.userDAO().getById(userId)
            withContext(Dispatchers.Main) {
                lambda(user)
            }
        }
    }

    fun insertCard(id: Long, card: Card, totalPrice: Double, quantity: Int) {
        val storedCard =
            StoredCard(0, id, card.name!!, quantity, totalPrice, card.purchaseUris!!["tcgplayer"]!!)
        GlobalScope.launch {
            database.wishListDAO().insert(storedCard)
        }
    }

    fun deleteCard(userId: Long, cardId: Long) {
        GlobalScope.launch {
            database.wishListDAO().delete(userId, cardId)
        }
    }

    fun updateUserCredentials(inputs: HashMap<String, TextInputEditText>, oldUser: User) {
        if (inputs["password"]?.text.toString().isEmpty()) {
            inputs["password"]?.setText(oldUser.password)
        }
        GlobalScope.launch {
            val newUser = User(
                oldUser.id,
                inputs["firstname"]?.text.toString(),
                inputs["lastname"]?.text.toString(),
                oldUser.username,
                inputs["email"]?.text.toString(),
                inputs["password"]?.text.toString(),
                oldUser.favoriteGuild,
                oldUser.favoriteColor
            )
            database.wishListDAO().update(newUser)
        }
    }
}
