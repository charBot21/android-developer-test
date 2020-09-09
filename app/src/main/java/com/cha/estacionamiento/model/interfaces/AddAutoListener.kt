package com.cha.estacionamiento.model.interfaces

interface AddAutoListener {
    fun onSuccess()

    fun onError()

    fun onShowProgress()

    fun onHideProgress()

    fun onClose()
}