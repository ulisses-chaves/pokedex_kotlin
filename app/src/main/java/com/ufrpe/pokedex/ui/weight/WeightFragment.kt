package com.ufrpe.pokedex.ui.weight

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.pokedex.R
import com.ufrpe.pokedex.adapter.DetailsPokemonAdapter
import com.ufrpe.pokedex.adapter.PokemonAdapter
import com.ufrpe.pokedex.adapter.WeightPokemonAdapter
import com.ufrpe.pokedex.model.Details
import com.ufrpe.pokedex.model.PokemonList
import com.ufrpe.pokedex.model.Results
import com.ufrpe.pokedex.repository.PokemonRepository
import com.ufrpe.pokedex.service.ApiService
import com.ufrpe.pokedex.service.PokemonApi
import com.ufrpe.pokedex.service.PokemonEndpoint
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_weight.view.*
import retrofit2.Call
import retrofit2.Response

class WeightFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_weight, container, false)
    val rv = root.rv_weight
    val layoutmanager = LinearLayoutManager(context)
    rv.layoutManager = layoutmanager

    var pagination = 0
    allPokemons(rv, pagination, false)

    //botoes direita e esq

    var flag = true
    val change = root.btChangePosition
    change.setOnClickListener {
      if (!flag) {
        flag = true
        allPokemons(rv, pagination, flag)
      } else {
        flag = false
        allPokemons(rv, pagination, flag)
      }
    }

    return root
  }

  fun allPokemons(rv : RecyclerView, pagination: Int, flag : Boolean) {
    Thread(Runnable {
      fillRV(rv, pagination, flag)
    }).start()
  }

  private fun fillRV(rv : RecyclerView, l : Int, flag : Boolean) {
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
    var asas :List<Details>
    if (flag) {
      asas = listpokemon.sortedByDescending { it.weight.toInt() }
    } else {
      asas = listpokemon.sortedBy { it.weight.toInt() }
    }
    rv.post {
      rv.adapter = WeightPokemonAdapter(this, asas)
    }

  }
}
