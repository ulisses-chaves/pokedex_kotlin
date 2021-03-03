package com.ufrpe.pokedex.ui.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ufrpe.pokedex.R
import kotlinx.android.synthetic.main.fragment_weight.view.*

class TypeFragment : Fragment() {

  private lateinit var dashboardViewModel: DashboardViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val root = inflater.inflate(R.layout.fragment_type, container, false)
    val rv = root.rv_weight
    val layoutmanager = LinearLayoutManager(context)
    rv.layoutManager = layoutmanager
    
    return root
  }
}