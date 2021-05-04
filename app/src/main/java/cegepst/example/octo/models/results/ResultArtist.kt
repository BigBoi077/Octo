package cegepst.example.octo.models.results

import com.google.gson.annotations.SerializedName

class ResultArtist(
        @SerializedName("data") val artistName: List<String>
)
