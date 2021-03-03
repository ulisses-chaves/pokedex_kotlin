package com.ufrpe.pokedex.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.pokedex.R
import com.ufrpe.pokedex.adapter.PokemonAdapter
import com.ufrpe.pokedex.model.Details
import com.ufrpe.pokedex.repository.FavoritePokemonRepository
import kotlinx.android.synthetic.main.fragment_weight.view.*

class FavoriteFragment : Fragment() {

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

    return root
  }

  fun allPokemons(rv : RecyclerView, pagination: Int, flag : Boolean) {
    fillRV(rv, pagination, flag)
  }

  private fun fillRV(rv : RecyclerView, l : Int, flag : Boolean) {
    var list : List <Details> = FavoritePokemonRepository.getList()
    rv.post {
      rv.adapter = PokemonAdapter(list)
    }

  }
}
