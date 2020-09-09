package com.cha.estacionamiento.ui.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cha.estacionamiento.data.AddAutoRoomDatabase
import com.cha.estacionamiento.data.local.entity.AddAutos
import com.cha.estacionamiento.data.local.repository.AddAutosRepository
import com.cha.estacionamiento.model.interfaces.HomeListener

class HomeViewModel(application: Application): AndroidViewModel(application) {

    // Listener
    var homeListener: HomeListener ?= null

    // Database
    private val repository: AddAutosRepository
    val allItems: LiveData<List<AddAutos>>

    init {
        val addAutoDao = AddAutoRoomDatabase.getDatabase(application).addAutosDao()
        repository = AddAutosRepository(addAutoDao)
        allItems = repository.allAutos
    }

    fun openAddAutos(view: View) {
        homeListener?.addAuto()
    }

}