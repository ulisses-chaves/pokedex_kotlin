package com.ufrpe.pokedex.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ufrpe.pokedex.R
import com.ufrpe.pokedex.adapter.PokemonAdapter
import com.ufrpe.pokedex.model.Pokemon
import com.ufrpe.pokedex.model.PokemonList
import com.ufrpe.pokedex.service.ApiService
import com.ufrpe.pokedex.service.PokemonEndpoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {

  private lateinit var homeViewModel: HomeViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    //homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_home, container, false)
    //val textView: TextView = root.findViewById(R.id.text_home)
    //homeViewModel.text.observe(viewLifecycleOwner, Observer {
    //  textView.text = it
    //})
    pokeList(root)
    //rv.setHasFixedSize(true)
    //rv.layoutManager = LinearLayoutManager(root.context)
    return root
  }

  private fun pokeList(root:View) {
    val retrofitClient = ApiService.getRetrofitInstance("https://pokeapi.co/api/v2/")
    val endpoint = retrofitClient.create(PokemonEndpoint::class.java)
    val callback= endpoint.getPokemonList()
    callback.enqueue(object : retrofit2.Callback<PokemonList>{
      override fun onFailure(call: Call<PokemonList>, t: Throwable) {
        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
      }

      override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {

        fillRV(response.body(), root, context)
      }

    })

  }

  private fun fillRV(pokemonList: PokemonList?, root: View, context: Context?) {
    val rv = root.rv
    rv.adapter = PokemonAdapter(pokemonList, context)
    rv.layoutManager = LinearLayoutManager(root.context)
  }
}


