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

class FeedViewModel: BaseViewModel() {

    internal var cards = MutableLiveData(listOf<Card>())

    internal fun getCards(): LiveData<List<Card>> {
        return cards
    }

    fun fetchSingleCard(cardId: String?, lambda: (Card) -> Unit) {
        scryfallService.getSingleCard(cardId)
            .enqueue(object : Callback<Card> {
                override fun onResponse(call: Call<Card>, response: Response<Card>) {
                    val card = Card(
                        response.body()?.id,
                        response.body()?.name,
                        response.body()?.set,
                        response.body()?.released,
                        response.body()?.imageUris,
                        response.body()?.manaCost,
                        response.body()?.convertedManaCost,
                        response.body()?.oracleText,
                        response.body()?.power,
                        response.body()?.toughness,
                        response.body()?.legalities,
                        response.body()?.setName,
                        response.body()?.rarity,
                        response.body()?.artist,
                        response.body()?.edhrecRank,
                        response.body()?.prices,
                        response.body()?.purchaseUris,
                        response.body()?.collectorNumber,
                        response.body()?.typeLine,
                        response.body()?.isReserved
                    )
                    lambda(card)
                }

                override fun onFailure(call: Call<Card>, t: Throwable) {
                    Log.d("LOADING FAILURE SINGLE CARD", t.message.toString())
                }
            })
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
                    Log.d("LOADING FAILURE RANDOM CARD BY COLOR", t.message.toString())
                }
            })
    }

    fun fetchCommanderCards() {
        scryfallService.getRandomCommanders(Formatter.IS_COMMANDER)
                .enqueue(object : Callback<CardResult> {
                    override fun onResponse(call: Call<CardResult>, response: Response<CardResult>) {
                        if (cards.value!!.isEmpty()) {
                            cards.value = response.body()!!.cards
                        } else {
                            makeTempList(cards, response)
                        }
                    }

                    override fun onFailure(call: Call<CardResult>, t: Throwable) {
                        Log.d("LOADING FAILURE COMMANDER CARDS", t.message.toString())
                    }
                })
    }

    fun fetchRandomCards(callback: (String) -> Unit = {}) {
        scryfallService.getRandomArtist()
                .enqueue(object : Callback<ResultArtist> {
                    override fun onResponse(call: Call<ResultArtist>, response: Response<ResultArtist>) {
                        val randomArtist = response.body()!!.artistName.random()
                        callback(randomArtist)
                        fetchRandomCardsAccordingToArtist(randomArtist)
                    }

                    override fun onFailure(call: Call<ResultArtist>, t: Throwable) {
                        Log.d("LOADING FAILURE RANDOM ARTIST", t.message.toString())
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
                        Log.d("LOADING FAILURE RANDOM CARDS BY ARTIST", t.message.toString())
                    }
                })
    }

    private fun makeTempList(cards: MutableLiveData<List<Card>>, response: Response<CardResult>) {
        val list = cards.value as ArrayList<Card>
        list.addAll(response.body()!!.cards)
        cards.value = list
        (cards.value as ArrayList<Card>).shuffle()
    }
}
