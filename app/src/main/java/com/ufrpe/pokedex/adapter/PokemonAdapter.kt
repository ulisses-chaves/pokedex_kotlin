package com.ufrpe.pokedex.adapter

import android.content.Intent
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ufrpe.pokedex.DetailsActivity
import com.ufrpe.pokedex.R
import com.ufrpe.pokedex.model.Details
import com.ufrpe.pokedex.model.Results
import com.ufrpe.pokedex.repository.PokemonRepository
import com.ufrpe.pokedex.service.ApiService
import com.ufrpe.pokedex.service.PokemonEndpoint
import com.ufrpe.pokedex.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_weight.view.*
import kotlinx.android.synthetic.main.item_main.view.*
import retrofit2.Call
import retrofit2.Response

class PokemonAdapter(pokemons : List<Details>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> () {
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
        var pokemon :Details = pokemons[position]
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
                this.txtNumber.text = pokemon.id
                this.txtWeight.text = pokemon.weight
                Glide.with(context).load(pokemon.sprites.front_default).into(this.pokemonVIew)
                var flag = false
                this.btFavorite.setOnClickListener {
                    if(!flag){
                        flag = true
                        this.btFavorite.setColorFilter(Color.RED)
                        PokemonRepository.addList(pokemon)
                        Log.d("asd", PokemonRepository.getList()[0].name)
                    } else {
                        flag = false
                        this.btFavorite.setColorFilter(Color.WHITE)
                        PokemonRepository.remList(pokemon)
                    }
                }
                this.btEye.setOnClickListener {
                    val intent = Intent(rootView.context, DetailsActivity::class.java)
                    val dados = Bundle()
                    dados.putString("name", pokemon.name)
                    dados.putString("id", pokemon.id)
                    dados.putString("weight", pokemon.weight)
                    dados.putString("height", pokemon.height)
                    dados.putString("type", pokemon.type?.name)
                    dados.putString("sprite", pokemon.sprites.front_default)
                    intent.putExtras(dados)
                    context.startActivity(intent)

                }
            }
        }
    }

}