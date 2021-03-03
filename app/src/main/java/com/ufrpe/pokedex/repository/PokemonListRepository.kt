package com.ufrpe.pokedex.repository

import com.ufrpe.pokedex.model.Details
import com.ufrpe.pokedex.model.PokemonList

object PokemonListRepository {
    var pokemonsList : PokemonList = PokemonList("", "", "", emptyList())

    fun setList (pokemonList: PokemonList) {
        pokemonsList = pokemonList
    }

    fun getList() : PokemonList{
        return pokemonsList
    }
}