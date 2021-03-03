package com.ufrpe.pokedex.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.pokedex.R
import com.ufrpe.pokedex.adapter.PokemonAdapter
import com.ufrpe.pokedex.repository.PokemonListRepository
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

  var loading = false
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_home, container, false)
    val rv = root.rv
    val layoutmanager = LinearLayoutManager(context)
    rv.layoutManager = layoutmanager
    pokeList(rv)


    rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if(dy>0) {
          val visibleItemCount = layoutmanager.childCount
          val pastVisibleItem = layoutmanager.findFirstCompletelyVisibleItemPosition()
          val total = layoutmanager.childCount

          if(!loading) {
            if((visibleItemCount + pastVisibleItem) >= total){
              pokeList(rv)
            }
          }
        }

        super.onScrolled(recyclerView, dx, dy)
      }
    })

    var flag = true
    val change = root.imageButton
    change.setOnClickListener {
      if (!flag) {
        flag = true
        rv.post {
          rv.adapter = PokemonAdapter(PokemonListRepository.getListCrescent())
        }
      } else {
        flag = false
        rv.adapter = PokemonAdapter(PokemonListRepository.getListDrec())
      }
    }

    return root
  }

  private fun pokeList(rv : RecyclerView) {
    loading = true
    rv.post {
      rv.adapter = PokemonAdapter(PokemonListRepository.getList())
    }
  }
}


