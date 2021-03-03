package com.ufrpe.pokedex.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ufrpe.pokedex.R
import com.ufrpe.pokedex.model.Details
import com.ufrpe.pokedex.model.Results
import com.ufrpe.pokedex.service.ApiService
import com.ufrpe.pokedex.service.PokemonEndpoint
import com.ufrpe.pokedex.ui.home.HomeFragment
import kotlinx.android.synthetic.main.item_main.view.*
import retrofit2.Call
import retrofit2.Response

class PokemonAdapter(context: HomeFragment, pokemons : List<Details>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> () {
    var context : HomeFragment = context
    private var pokemons = pokemons
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int = pokemons.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        /*val retrofitClient = ApiService.getRetrofitInstance("https://pokeapi.co/api/v2/pokemon/")
        val endpoint = retrofitClient.create(PokemonEndpoint::class.java)
        val callback= endpoint.getDetails(pokemons[position].name)
        callback.enqueue(object : retrofit2.Callback<Details> {
            override fun onFailure(call: Call<Details>, t: Throwable) {
                //Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }


            override fun onResponse(call: Call<Details>, response: Response<Details>) {*/
                val pokemon :Details = pokemons[position]
                holder.bind(pokemon)

                //holder.itemView.txtName.text = response.body()!!.name
                //holder.itemView.txtNumberMain.text = response.body()!!.id
                //Glide.with(context).load(response.body()!!.sprites.front_default).into(holder.itemView.pokemonVIew)
            }
        //})
    //}

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Details) {
            with(itemView){
                this.txtName.text = pokemon.name
                this.txtNumberMain.text = pokemon.id
                Glide.with(context).load(pokemon.sprites.front_default).into(this.pokemonVIew)
            }
        }
    }

}