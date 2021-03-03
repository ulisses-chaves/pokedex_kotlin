package com.ufrpe.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ufrpe.pokedex.R
import com.ufrpe.pokedex.model.Details
import com.ufrpe.pokedex.model.Results
import com.ufrpe.pokedex.repository.PokemonRepository
import com.ufrpe.pokedex.service.ApiService
import com.ufrpe.pokedex.service.PokemonEndpoint
import com.ufrpe.pokedex.ui.home.HomeFragment
import com.ufrpe.pokedex.ui.weight.WeightFragment
import kotlinx.android.synthetic.main.item_main.view.*
import kotlinx.android.synthetic.main.item_main.view.pokemonVIew
import kotlinx.android.synthetic.main.item_main.view.txtName
import kotlinx.android.synthetic.main.item_weight.view.*
import retrofit2.Call
import retrofit2.Response

class WeightPokemonAdapter (context: WeightFragment, pokemons : List<Details>) : RecyclerView.Adapter<WeightPokemonAdapter.WeightViewHolder> (){

    private var allPokemons : List<Details> = pokemons

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeightViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weight, parent, false)
        return WeightViewHolder(view)
    }

    override fun getItemCount(): Int = allPokemons.size

    override fun onBindViewHolder(holder: WeightViewHolder, position: Int) {
        val pokemon :Details = allPokemons[position]
        holder.bind(pokemon)

        /*val retrofitClient = ApiService.getRetrofitInstance("https://pokeapi.co/api/v2/pokemon/")
        val endpoint = retrofitClient.create(PokemonEndpoint::class.java)
        val callback= endpoint.getDetails(allPokemons[position].name)
        callback.enqueue(object : retrofit2.Callback<Details> {
            override fun onFailure(call: Call<Details>, t: Throwable) {
                //Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Details>, response: Response<Details>) {
                val details : Details = response.body()!!
                //PokemonRepository.addList(response.body()!!)
                //detailsListPokemon.detailsPokemons.add(details)
                holder.itemView.txtName.text = details.name
                holder.itemView.txtWeight.text = details.weight
                Glide.with(context).load(details.sprites.front_default).into(holder.itemView.pokemonVIew)
            }
        })*/
    }



    inner class WeightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Details) {
            with(itemView){
                this.txtName.text = pokemon.name
                this.txtWeight.text = pokemon.weight
                Glide.with(context).load(pokemon.sprites.front_default).into(this.pokemonVIew)
            }
        }
    }
}