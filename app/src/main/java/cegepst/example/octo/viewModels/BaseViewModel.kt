package cegepst.example.octo.viewModels

import androidx.lifecycle.ViewModel
import cegepst.example.octo.models.base.Card
import cegepst.example.octo.models.stored.StoredCard
import cegepst.example.octo.models.stored.User
import cegepst.example.octo.services.ScryfallService
import cegepst.example.octo.stores.AppStore
import cegepst.example.octo.views.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel: ViewModel() {

    private lateinit var database: AppStore
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
        val storedCard = StoredCard(0, id, card.name!!, quantity, totalPrice)
        GlobalScope.launch {
            database.wishListDAO().insert(storedCard)
        }
    }
}
