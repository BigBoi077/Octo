package cegepst.example.octo.services

import cegepst.example.octo.models.base.Card
import cegepst.example.octo.models.results.CardResult
import cegepst.example.octo.models.results.ResultArtist
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.scryfall.com"
private const val CARD_ENDPOINT = "cards"
private const val SEARCH_ENDPOINT = "search"
private const val RANDOM_ENDPOINT = "random"
private const val COLLECTION_ENDPOINT = "collection"
private const val CATALOG_ENDPOINT = "catalog"
private const val ARTIST_ENDPOINT = "artist-names"

interface ScryfallService {

    @GET("${CARD_ENDPOINT}/${SEARCH_ENDPOINT}")
    fun getRandomCards(
            @Query("q") flag: String
    ): Call<CardResult>

    @GET("${CARD_ENDPOINT}/${RANDOM_ENDPOINT}")
    fun getSingleCard(): Call<Card>

    @GET("${CARD_ENDPOINT}/${RANDOM_ENDPOINT}")
    fun getSingleCommanderCard(
        @Query("q") flag: String
    ): Call<Card>

    @GET("${CATALOG_ENDPOINT}/${ARTIST_ENDPOINT}")
    fun getRandomArtist(): Call<ResultArtist>

    @GET("${CARD_ENDPOINT}/${SEARCH_ENDPOINT}")
    fun getRandomCardsAccordingToArtist(
        @Query("q") artistName: String
    ): Call<CardResult>

    @GET("${CARD_ENDPOINT}/${SEARCH_ENDPOINT}")
    fun getRandomCommanders(
        @Query("q") commanderQuery: String
    ):Call<CardResult>

    companion object {
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