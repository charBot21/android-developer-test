package com.cha.estacionamiento.model.interfaces

import com.cha.estacionamiento.data.local.entity.AddAutos

interface HomeListener {
    fun onShowProgress()

    fun onHideProgress()

    fun addAuto()

    fun autoClicked( autos: AddAutos, position: Int )
}