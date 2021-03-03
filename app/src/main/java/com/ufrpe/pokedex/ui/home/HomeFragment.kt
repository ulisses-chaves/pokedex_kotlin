package com.ufrpe.pokedex.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.pokedex.R
import com.ufrpe.pokedex.adapter.PokemonAdapter
import com.ufrpe.pokedex.model.Details
import com.ufrpe.pokedex.service.PokemonApi
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
  //private  var listpokemon : PokemonAdapter = PokemonAdapter(this)
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_home, container, false)
    val rv = root.rv
    val layoutmanager = LinearLayoutManager(context)
    rv.layoutManager = layoutmanager

    var pagination = 0
    pokeList(root, rv, pagination)

    val rightBtn = root.btRight
    rightBtn.setOnClickListener {
      pagination += 20
      pokeList(root, rv, pagination)
    }

    val leftBtn = root.btLeft
    leftBtn.setOnClickListener {
      if (pagination != 0) {
        pagination -= 20
        pokeList(root, rv, pagination)
      }
    }
    return root
  }

  private fun pokeList(root : View, rv : RecyclerView, pagination : Int) {
    /*val retrofitClient = ApiService.getRetrofitInstance("https://pokeapi.co/api/v2/")
    val endpoint = retrofitClient.create(PokemonEndpoint::class.java)
    val callback= endpoint.getPokemonList(pagination,20)
    callback.enqueue(object : retrofit2.Callback<PokemonList>{
      override fun onFailure(call: Call<PokemonList>, t: Throwable) {
        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
      }

      override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
        val pokemonList: PokemonList? = response.body()
        val pokemons : List<Results> = pokemonList!!.results
        listpokemon.addListPokemon(pokemons)
        fillRV( root, context)
      }

    })*/
    Thread(Runnable {
      fillRV(root, rv, pagination)
    }).start()
  }



  private fun fillRV(root: View, rv: RecyclerView, l : Int) {
    var list = PokemonApi.getPokemonList(l)
    val listpokemon: List<Details> = list.results.map { rs->
      val pokemon = PokemonApi.getPokemons(rs.name)
      Log.d("asd", pokemon.name)
        Details(
                pokemon.name,
                pokemon.id,
                pokemon.weight,
                pokemon.height,
                pokemon.type,
                pokemon.sprites
        )
      }
      rv.post {
        rv.adapter = PokemonAdapter( listpokemon)
      }
  }
}


