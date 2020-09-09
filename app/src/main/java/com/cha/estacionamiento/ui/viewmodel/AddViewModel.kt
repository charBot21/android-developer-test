package com.cha.estacionamiento.ui.viewmodel

import android.app.Application
import android.view.View
import android.widget.RadioButton
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cha.estacionamiento.R
import com.cha.estacionamiento.data.AddAutoRoomDatabase
import com.cha.estacionamiento.data.local.entity.AddAutos
import com.cha.estacionamiento.data.local.repository.AddAutosRepository
import com.cha.estacionamiento.model.interfaces.AddAutoListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(application: Application): AndroidViewModel(application) {

    // Listener
    var addListener: AddAutoListener ?= null

    // Inputs
    val placaAuto = ObservableField<String?>("")

    // Database
    private val repository: AddAutosRepository

    private var flagRadioButton: Boolean = false
    lateinit var tipoAuto: String
    var tarifa: Double ?= null
    var placa: String = ""

    init {
        val addAutosDao = AddAutoRoomDatabase.getDatabase(application).addAutosDao()
        repository = AddAutosRepository(addAutosDao)
    }

    fun onRadioButtonClicked(view: View) {
        if ( view is RadioButton ) {
            val checked = view.isChecked

            when ( view.getId() ) {
                R.id.rb_oficial ->
                    if (checked) {
                        flagRadioButton = true
                        tipoAuto = "Oficial"
                        tarifa = 0.0
                    }
                R.id.rb_residente ->
                    if ( checked ) {
                        flagRadioButton = true
                        tipoAuto = "Residente"
                        tarifa = 0.05
                    }
                R.id.rb_no_residente ->
                    if ( checked ) {
                        flagRadioButton = true
                        tipoAuto = "No Residente"
                        tarifa = 0.5
                    }
            }
        }
    }


    fun addAutos(view: View) {
        addListener?.onShowProgress()
        if ( !placaAuto.get().isNullOrEmpty() && flagRadioButton ) {
            addListener?.onSuccess()
            placa = placaAuto.get().toString()
        } else {
            addListener?.onError()
        }
        addListener?.onHideProgress()
    }

    fun closeDetail(view: View) {
        addListener?.onClose()
    }

    fun insert(addAutos: AddAutos) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertAuto(addAutos)
    }
}