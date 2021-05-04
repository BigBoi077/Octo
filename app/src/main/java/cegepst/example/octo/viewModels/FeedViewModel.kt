package cegepst.example.octo.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cegepst.example.octo.models.base.Card
import cegepst.example.octo.models.helpers.Formatter
import cegepst.example.octo.models.results.CardResult
import cegepst.example.octo.models.results.ResultArtist
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class FeedViewModel: BaseViewModel() {

    internal var cards = MutableLiveData(listOf<Card>())
    internal var cardList = ArrayList<Card>()

    internal fun getCards(): LiveData<List<Card>> {
        return cards
    }

    fun fetchCardsByRandomColor() {
        scryfallService.getRandomCards(Formatter.getRandomColorQuery())
                .enqueue(object : Callback<CardResult> {
                    override fun onResponse(call: Call<CardResult>, response: Response<CardResult>) {
                        if (cards.value!!.isEmpty()) {
                            cards.value = response.body()!!.cards
                        } else {
                            makeTempList(cards, response)
                        }
                    }

                    override fun onFailure(call: Call<CardResult>, t: Throwable) {
                        Log.d("LOADING FAILURE", t.message.toString())
                    }
                })
    }

    fun fetchRandomCards() {
        scryfallService.getRandomArtist()
                .enqueue(object : Callback<ResultArtist> {
                    override fun onResponse(call: Call<ResultArtist>, response: Response<ResultArtist>) {
                        val randomArtist = response.body()!!.artistName.random()
                        fetchRandomCardsAccordingToArtist(randomArtist)
                    }

                    override fun onFailure(call: Call<ResultArtist>, t: Throwable) {
                        Log.d("LOADING FAILURE", t.message.toString())
                    }
                })
    }

    fun fetchRandomCardsAccordingToArtist(artistName: String ) {
        scryfallService.getRandomCardsAccordingToArtist(Formatter.formatArtistSearch(artistName))
                .enqueue(object : Callback<CardResult> {
                    override fun onResponse(call: Call<CardResult>, response: Response<CardResult>) {
                        if (cards.value!!.isEmpty()) {
                            cards.value = response.body()!!.cards
                        } else {
                            makeTempList(cards, response)
                        }
                    }

                    override fun onFailure(call: Call<CardResult>, t: Throwable) {
                        Log.d("LOADING FAILURE", t.message.toString())
                    }
                })
    }

    private fun makeTempList(cards: MutableLiveData<List<Card>>, response: Response<CardResult>) {
        val list = cards.value as ArrayList<Card>
        list.addAll(response.body()!!.cards)
        cards.value = list
    }
}
