package cegepst.example.octo.models.base

import com.google.gson.annotations.SerializedName

class Set (
        @SerializedName("code") val code: String,
        @SerializedName("name") val name: String
)
