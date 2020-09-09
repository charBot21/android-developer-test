package com.cha.estacionamiento.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cha.estacionamiento.R
import com.cha.estacionamiento.adapter.AutosAdapter
import com.cha.estacionamiento.data.local.entity.AddAutos
import com.cha.estacionamiento.databinding.ActivityHomeBinding
import com.cha.estacionamiento.model.interfaces.HomeListener
import com.cha.estacionamiento.ui.utils.hide
import com.cha.estacionamiento.ui.utils.show
import com.cha.estacionamiento.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeListener {

    // Data binding and View Model
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding

    // Recycler View
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Data Binding and View Model
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.homevm = viewModel
        viewModel.homeListener = this

        // RecyclerView
        recyclerView = findViewById(R.id.rv_items)
        onLoadData()

    }

    private fun onLoadData() {
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        viewModel.allItems.observe(this, Observer { items ->
            items?.let {
                val adapter = AutosAdapter(it, this)
                recyclerView.adapter = adapter
            }
        })
    }

    override fun onShowProgress() {
        pb_home.show()
    }

    override fun onHideProgress() {
        pb_home.hide()
    }

    override fun addAuto() {
        val intentAdd = Intent(this, AddActivity::class.java)
        startActivity(intentAdd)
    }

    override fun autoClicked(autos: AddAutos, position: Int) {
        val intentItem = Intent(this, DetailActivity::class.java)
        intentItem.putExtra("placa", autos.placa)
        intentItem.putExtra("tipo", autos.tipoAuto)
        intentItem.putExtra("hora", autos.horaIngreso)
        intentItem.putExtra("tarifa", autos.tarifaAuto.toString())
        intentItem.putExtra("fecha_ingreso", autos.fechaIngreso)
        intentItem.putExtra("status", autos.statusAuto)
        startActivity(intentItem)
    }
}