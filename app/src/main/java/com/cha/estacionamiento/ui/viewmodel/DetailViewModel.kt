package com.cha.estacionamiento.ui.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cha.estacionamiento.data.AddAutoRoomDatabase
import com.cha.estacionamiento.data.local.entity.AddAutos
import com.cha.estacionamiento.data.local.repository.AddAutosRepository
import com.cha.estacionamiento.model.interfaces.DetailListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): AndroidViewModel(application) {

    // Listener
    var detailListener: DetailListener ?= null

    // Database
    private val repository: AddAutosRepository

    init {
        val autosDao = AddAutoRoomDatabase.getDatabase(application).addAutosDao()
        repository = AddAutosRepository(autosDao)
    }

    fun exitAuto(view: View) {
        detailListener?.onSuccess()
    }


    fun closeDetail(view: View) {
        detailListener?.onCloseDetail()
    }

    fun insert(addAutos: AddAutos) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertAuto(addAutos)
    }
}