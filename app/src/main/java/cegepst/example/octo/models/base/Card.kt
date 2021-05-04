package cegepst.example.octo.models.base

import com.google.gson.annotations.SerializedName

class Card (
        @SerializedName("id") val id: String?,
        @SerializedName("name") val name: String?,
        @SerializedName("set") val set: String?,
        @SerializedName("released_at") val released: String?,
        @SerializedName("image_uris") val imageUris: HashMap<String, String>?,
        @SerializedName("mana_cost") val manaCost: String?,
        @SerializedName("cmc") val convertedManaCost: String?,
        @SerializedName("oracle_text") val oracleText: String?,
        @SerializedName("power") val power: String?,
        @SerializedName("toughness") val toughness: String?,
        @SerializedName("legalities") val legalities: HashMap<String, String>?,
        @SerializedName("set_name") val setName: String?,
        @SerializedName("rarity") val rarity: String?,
        @SerializedName("artist") val artist: String?,
        @SerializedName("edhrec_rank") val edhrecRank: Int?,
        @SerializedName("prices") val prices: HashMap<String, String>?,
        @SerializedName("purchase_uris") val purchaseUris: HashMap<String, String>?
)
