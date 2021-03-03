package com.ufrpe.pokedex.service

import me.sargunvohra.lib.pokekotlin.client.PokeApi
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object {
        /** Retorna uma Instância do Client Retrofit para Requisições
         * @param path Caminho Principal da API
         */
        fun getRetrofitInstance(path : String) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        //fun getPokeApiInstance(): PokeApiClient {
          //  val pokeApi = PokeApiClient()
           // return pokeApi
            //val bulbasaur = pokeApi.getPokemonSpecies(1)
            //println(bulbasaur)
        }
    //}
}