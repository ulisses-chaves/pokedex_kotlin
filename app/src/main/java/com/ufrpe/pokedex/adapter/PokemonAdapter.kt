package com.ufrpe.pokedex.adapter

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ufrpe.pokedex.DetailsActivity
import com.ufrpe.pokedex.R
import com.ufrpe.pokedex.model.Details
import com.ufrpe.pokedex.repository.PokemonListRepository
import com.ufrpe.pokedex.repository.FavoritePokemonRepository
import kotlinx.android.synthetic.main.item_main.view.*

class PokemonAdapter(pokemons : List<Details>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> () {
    private var pokemons = pokemons
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int = pokemons.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        var pokemon :Details = pokemons[position]
        holder.bind(pokemon)
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Details) {
            with(itemView){
                this.txtName.text = pokemon.name
                this.txtNumber.text = pokemon.id
                this.txtWeight.text = pokemon.weight
                Glide.with(context).load(pokemon.sprites.front_default).into(this.pokemonVIew)

                val chosenPokemon = PokemonListRepository.getList()[pokemon.id.toInt() - 1]

                if(!chosenPokemon.fav){
                    this.btFavorite.setColorFilter(Color.WHITE)
                } else {
                    this.btFavorite.setColorFilter(Color.RED)
                }

                this.btFavorite.setOnClickListener {
                    if(!chosenPokemon.fav){
                        PokemonListRepository.setFav(pokemon.id.toInt() - 1, true)
                        this.btFavorite.setColorFilter(Color.RED)
                        FavoritePokemonRepository.addList(pokemon)
                    } else {
                        PokemonListRepository.setFav(pokemon.id.toInt() - 1, false)
                        this.btFavorite.setColorFilter(Color.WHITE)
                        FavoritePokemonRepository.remList(pokemon)
                    }
                }
                this.btEye.setOnClickListener {
                    val intent = Intent(rootView.context, DetailsActivity::class.java)
                    val dados = Bundle()
                    dados.putString("name", pokemon.name)
                    dados.putString("id", pokemon.id)
                    dados.putString("weight", pokemon.weight)
                    dados.putString("height", pokemon.height)
                    dados.putString("type", pokemon.types?.get(0)?.type!!.name)
                    if(pokemon.types.size == 2) {
                        dados.putString("type1", pokemon.types?.get(1)?.type!!.name)
                    }
                    dados.putString("sprite", pokemon.sprites.front_default)
                    intent.putExtras(dados)
                    context.startActivity(intent)

                }
            }
        }
    }

}