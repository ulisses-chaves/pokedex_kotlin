package com.ufrpe.pokedex.model

import Type
import android.net.Uri
import com.google.gson.annotations.SerializedName

data class Pokemon (
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("details") val details: Details
)