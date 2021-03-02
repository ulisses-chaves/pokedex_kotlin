package com.ufrpe.pokedex.model

import com.google.gson.annotations.SerializedName
import com.ufrpe.pokedex.model.Pokemon

data class PokemonList (
    @SerializedName("count") val count: String,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val pokemons: List<Pokemon>
)