package com.ufrpe.pokedex.service

import com.ufrpe.pokedex.model.Details
import com.ufrpe.pokedex.model.PokemonList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonEndpoint {
    @GET("pokemon/")
    fun getPokemonList() : Call<PokemonList>

    @GET("{id}/")
    fun getDetails(@Path("id") id: String) : Call<Details>

    //@GET("latest")
    //fun getConversao(@Query("base") moedaOrigem : String) : Call<Cotacao>
}