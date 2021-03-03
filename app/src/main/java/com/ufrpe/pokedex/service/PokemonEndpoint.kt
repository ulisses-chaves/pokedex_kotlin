package com.ufrpe.pokedex.service

import com.ufrpe.pokedex.model.Details
import com.ufrpe.pokedex.model.PokemonList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonEndpoint {
    @GET("pokemon/")
    fun getPokemonList(@Query("offset") n: Int, @Query("limit") l : Int) : Call<PokemonList>

    @GET("pokemon/{id}/")
    fun getDetails(@Path("id") id: String) : Call<Details>

    @GET("pokemon/")
    fun getAllPokemons(@Query("offset") n: Int, @Query("limit") l : Int) : Call<PokemonList>

    //@GET("latest")
    //fun getConversao(@Query("base") moedaOrigem : String) : Call<Cotacao>
}