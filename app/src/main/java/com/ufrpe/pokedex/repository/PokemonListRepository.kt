package com.ufrpe.pokedex.repository

import com.ufrpe.pokedex.model.Details

object PokemonListRepository {
    var pokemonsList : MutableList<Details> = mutableListOf()

    fun addList (details: Details) {
        pokemonsList.add(details)
    }

    fun getList() : MutableList<Details>{
        return pokemonsList
    }

    fun setFav(id : Int, flag : Boolean) {
        if (!flag) {
            pokemonsList.get(id).fav = false
        } else {
            pokemonsList.get(id).fav = true
        }
    }

    fun getListCrescent() : List<Details> {
        val newList = pokemonsList.sortedBy { it.weight.toInt() }
        return newList
    }

    fun getListDrec() : List<Details> {
        val newList = pokemonsList.sortedByDescending { it.weight.toInt() }
        return newList
    }
}