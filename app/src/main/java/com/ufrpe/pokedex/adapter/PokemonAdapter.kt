package com.ufrpe.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.pokedex.R
import com.ufrpe.pokedex.model.PokemonList
import com.ufrpe.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.item_main.view.*

class PokemonAdapter(private val pokemonList: PokemonList?, private val context: Context?) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int = pokemonList!!.pokemons.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        //Glide.with(context!!).load(pokemons!![position].image).into(holder.itemView.pokemonVIew)
        holder.itemView.txtName.text = pokemonList!!.pokemons[position].name
        //holder.itemView.txtNumberMain.text = pokemons!![position].id
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon) {
            with(pokemon){
                itemView.txtName.text = pokemon.name
                //itemView.txtNumberMain.text = id as String
                //itemView.pokemonVIew.setImageURI(image as Uri)
            }
        }
    }



}