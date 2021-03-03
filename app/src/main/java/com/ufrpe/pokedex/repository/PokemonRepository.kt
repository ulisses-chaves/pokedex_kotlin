package com.ufrpe.pokedex.repository

import com.ufrpe.pokedex.model.Details

object PokemonRepository {
    private var pokemons : MutableList<Details> = mutableListOf()

    fun addList (details: Details) {
        pokemons.add(details)
    }

    fun getCrescentOrderList() : MutableList<Details> {
        pokemons.sortBy { it.weight }
        return pokemons
    }
}