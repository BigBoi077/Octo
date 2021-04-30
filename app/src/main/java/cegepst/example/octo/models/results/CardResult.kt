package cegepst.example.octo.models.results

import cegepst.example.octo.models.base.Card
import com.google.gson.annotations.SerializedName

class CardResult (
        @SerializedName("data") val cards: List<Card>,
        @SerializedName("next_page") val next: String
)
