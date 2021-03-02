package com.ufrpe.pokedex.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.ContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import com.ufrpe.pokedex.R
import com.ufrpe.pokedex.adapter.PokemonAdapter
import com.ufrpe.pokedex.model.PokemonList
import com.ufrpe.pokedex.model.Results
import com.ufrpe.pokedex.service.ApiService
import com.ufrpe.pokedex.service.PokemonEndpoint
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.item_main.view.*
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {
  private  var listpokemon : PokemonAdapter = PokemonAdapter(this)
  private lateinit var instanceRoot: View

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_home, container, false)
    var pagination = 0
    pokeList(root, pagination)


    val rightBtn = root.btRight
    rightBtn.setOnClickListener {
      pagination += 20
      pokeList(root, pagination)
    }

    val leftBtn = root.btLeft
    leftBtn.setOnClickListener {
      if (pagination != 0) {
        pagination -= 20
        pokeList(root, pagination)
      }
    }

    //val watch : ImageButton = root.findViewById(R.id.btEye)
    //watch.setOnClickListener {
 //
   // }

    //val fav  :ImageButton = root.findViewById(R.id.btFavorite)
    //fav.setOnClickListener {

    //}

    return root
  }

  private fun pokeList(root:View, pagination: Int) {
    val retrofitClient = ApiService.getRetrofitInstance("https://pokeapi.co/api/v2/")
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

    })
  }

  private fun fillRV(root: View, context: Context?) {
    val rv = root.rv
    rv.adapter = listpokemon
    rv.layoutManager = LinearLayoutManager(root.context)
  }
}


