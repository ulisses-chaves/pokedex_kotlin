package com.ufrpe.pokedex.repository

import com.ufrpe.pokedex.model.Details

object FavoritePokemonRepository {
    private var pokemons : MutableList<Details> = mutableListOf()

    fun addList (details: Details) {
        if(!pokemons.contains(details)){
            pokemons.add(details)
        }
    }

    fun remList(details: Details) {
        pokemons.remove(details)
    }

    fun getList() : MutableList<Details>{
        return pokemons
    }



}