package com.ufrpe.pokedex.model

import Sprites
import Types
import com.google.gson.annotations.SerializedName

data class Details (
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
    @SerializedName("weight") val weight: String,
    @SerializedName("height") val height: String,
    @SerializedName("types") val types: List<Types>? = null,
    @SerializedName("sprites") val sprites: Sprites,
    var fav : Boolean = false
)