package com.ufrpe.pokedex.model

import Sprites
import Type
import com.google.gson.annotations.SerializedName

data class Details (
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
    @SerializedName("weight") val weight: String,
    @SerializedName("height") val height: String,
    @SerializedName("type") val type: Type? = null,
    @SerializedName("sprites") val sprites: Sprites
)