package cegepst.example.octo.services

import cegepst.example.octo.models.base.Card
import cegepst.example.octo.models.results.CardResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.scryfall.com"
private const val CARD_ENDPOINT = "cards"
private const val SEARCH_ENDPOINT = "search"
private const val RANDOM_ENDPOINT = "random"
private const val COLLECTION_ENDPOINT = "collection"

interface ScryfallService {

    @GET("${CARD_ENDPOINT}/${RANDOM_ENDPOINT}")
    fun getRandomCards(
            @Query("q") flag: String
    ): Call<CardResult>

    @GET("${CARD_ENDPOINT}/${RANDOM_ENDPOINT}")
    fun getSingleCard(
            @Query("q") flag: String
    ): Call<Card>

    @GET("${CARD_ENDPOINT}/${RANDOM_ENDPOINT}")
    fun getSingleCommanderCard(
        @Query("q") flag: String
    ): Call<Card>

    companion object {

        const val COMMANDER_SEARCH = "is%3Acommander"

        fun create(): ScryfallService {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    ).baseUrl(BASE_URL)
                    .build()

            return retrofit.create(ScryfallService::class.java)
        }
    }
}