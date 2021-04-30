package cegepst.example.octo.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cegepst.example.octo.models.base.Card
import cegepst.example.octo.models.helpers.ColorQueryFormatter
import cegepst.example.octo.models.results.CardResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedViewModel: BaseViewModel() {

    internal var cards = MutableLiveData(listOf<Card>())

    internal fun getCards(): LiveData<List<Card>> {
        return cards
    }

    fun fetchCards() {
        scryfallService.getRandomCards(ColorQueryFormatter.getRandomColorQuery())
                .enqueue(object : Callback<CardResult> {
                    override fun onResponse(call: Call<CardResult>, response: Response<CardResult>) {
                        cards.value = response.body()!!.cards
                    }

                    override fun onFailure(call: Call<CardResult>, t: Throwable) {
                        Log.d("LOADING FAILURE", t.message.toString())
                    }
                })
    }
}
