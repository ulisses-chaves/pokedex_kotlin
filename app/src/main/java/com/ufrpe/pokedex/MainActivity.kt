package com.ufrpe.pokedex

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.ufrpe.pokedex.model.Details
import com.ufrpe.pokedex.model.PokemonList
import com.ufrpe.pokedex.model.Results
import com.ufrpe.pokedex.repository.PokemonListRepository
import com.ufrpe.pokedex.repository.PokemonRepository
import com.ufrpe.pokedex.service.ApiService
import com.ufrpe.pokedex.service.PokemonEndpoint
import kotlinx.android.synthetic.main.item_main.view.*
import kotlinx.android.synthetic.main.item_weight.view.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

}