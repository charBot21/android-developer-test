package com.cha.estacionamiento.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cha.estacionamiento.R
import com.cha.estacionamiento.data.local.entity.AddAutos
import com.cha.estacionamiento.databinding.ActivityDetailBinding
import com.cha.estacionamiento.model.interfaces.DetailListener
import com.cha.estacionamiento.ui.utils.getDateEnter
import com.cha.estacionamiento.ui.utils.getHour
import com.cha.estacionamiento.ui.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.recyclerview_autos.*

class DetailActivity : AppCompatActivity(), DetailListener {

    // Data binding and view model
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    // Data received
    private var placaR: String ?= null
    private var tipoAutoR: String ?= null
    private var horaIngresoR: String ?= null
    private var tarifaR: String ?= null
    private var fechaIngresoR: String ?= null
    private var statusR: String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding.detailvm = viewModel
        viewModel.detailListener = this

        getDataReceived()
    }

    private fun getDataReceived(){
        placaR = intent.extras?.getString("placa")
        tipoAutoR = intent.extras?.getString("tipo")
        horaIngresoR = intent.extras?.getString("hora")
        tarifaR = intent.extras?.getString("tarifa")
        fechaIngresoR = intent.extras?.getString("fecha_ingreso")
        statusR = intent.extras?.getString("status")

        tv_detail_placa.text = getString(R.string.placa) + ": " + placaR
        tv_detail_tipo_auto.text = getString(R.string.tipo_auto) + ": " + tipoAutoR
        tv_detail_hora_ingreso.text = getString(R.string.hora_ingreso) + ": " + horaIngresoR
        tv_detail_tarifa.text = getString(R.string.tarifa) + ": " + tarifaR
        tv_detail_fecha_ingreso.text = getString(R.string.fecha_ingreso) + ": " + fechaIngresoR
        tv_detail_status.text = getString(R.string.estatus) + ": " + statusR
        tv_detail_monto.text = getString(R.string.monto) + ": " 


        if ( statusR == "A" ) {
            btn_auto_exit.text = getString(R.string.exit_auto)
        } else {
            btn_auto_exit.text = getString(R.string.enter_auto)
        }
    }

    override fun onSuccess() {

        if ( statusR == "A" ) {
            statusR = "I"
        } else {
            horaIngresoR = getHour()
            fechaIngresoR = getDateEnter()
            statusR = "A"
        }

        val autos = AddAutos(
            placaR.toString(),
            tipoAutoR.toString(),
            statusR.toString(),
            horaIngresoR.toString(),
            fechaIngresoR.toString(),
            tarifaR.toString().toDouble()
        )

        viewModel.insert(autos)
        finish()
    }

    override fun onCloseDetail() {
        finish()
    }
}