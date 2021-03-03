package com.ufrpe.pokedex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_main.view.*

class DetailsActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val intent = intent
        val dados = intent.extras
        val name = dados!!.getString("name")
        val id = dados!!.getString("id")
        val height = dados!!.getString("height")
        val weight = dados!!.getString("weight")
        val type = dados!!.getString("type")
        val sprite = dados!!.getString("sprite")

        nameTxt.text = name
        numberTxt.text = "nº $id"
        heighttxt.text = "${height}cm"
        weightTxt.text = "${weight}kg"
        typeTxt.text = type
        Glide.with(this).load(sprite).into(imgPoke)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}