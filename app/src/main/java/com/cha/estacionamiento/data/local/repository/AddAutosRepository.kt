package com.cha.estacionamiento.data.local.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.cha.estacionamiento.data.local.dao.AddAutoDao
import com.cha.estacionamiento.data.local.entity.AddAutos

class AddAutosRepository( private val addAutoDao: AddAutoDao ) {

    val allAutos: LiveData<List<AddAutos>> = addAutoDao.getAutos()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAuto(addAutos: AddAutos) {
        addAutoDao.insertAuto(addAutos)
    }
}