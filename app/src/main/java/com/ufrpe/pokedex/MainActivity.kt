package com.ufrpe.pokedex

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ufrpe.pokedex.repository.PokemonListRepository
import com.ufrpe.pokedex.service.PokemonApi

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        Thread(Runnable {
            val list = PokemonApi.getAllPokemons()
            list.results.map {
                PokemonListRepository.addList(PokemonApi.getPokemons(it.name))
            }
        }).start()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        Thread.sleep(4000)
        navView.setupWithNavController(navController)
    }

}