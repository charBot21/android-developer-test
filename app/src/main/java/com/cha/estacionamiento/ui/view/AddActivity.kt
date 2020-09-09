package com.cha.estacionamiento.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cha.estacionamiento.R
import com.cha.estacionamiento.data.local.entity.AddAutos
import com.cha.estacionamiento.databinding.ActivityAddBinding
import com.cha.estacionamiento.model.interfaces.AddAutoListener
import com.cha.estacionamiento.ui.utils.*
import com.cha.estacionamiento.ui.viewmodel.AddViewModel
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity(), AddAutoListener {

    // Data binding and view model
    private lateinit var binding: ActivityAddBinding
    private lateinit var viewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add)
        viewModel = ViewModelProvider(this).get(AddViewModel::class.java)
        binding.addvm = viewModel
        viewModel.addListener = this
    }

    override fun onSuccess() {
        val placa = viewModel.placaAuto.get()
        val tipoAuto = viewModel.tipoAuto
        val hora : String = getHour()
        val fechaIngreso: String = getDateEnter()
        val tarifa = viewModel.tarifa
        val status = "A"

        val auto = AddAutos(
            placa.toString(),
            tipoAuto,
            status,
            hora,
            fechaIngreso,
            tarifa.toString().toDouble()
        )

        viewModel.insert(auto)
        finish()
    }

    override fun onError() {
        val errorMessage = getString(R.string.error_guardado)
        toast(errorMessage)
    }

    override fun onShowProgress() {
        pb_add.show()
    }

    override fun onHideProgress() {
        pb_add.hide()
    }

    override fun onClose() {
        finish()
    }
}