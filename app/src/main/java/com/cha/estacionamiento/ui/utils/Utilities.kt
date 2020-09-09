package com.cha.estacionamiento.ui.utils

import java.text.DateFormat
import java.util.*


fun getHour(): String {
    val current = DateFormat.getTimeInstance()
    return current.format(Date())
}

fun getDateEnter(): String {
    val current = DateFormat.getDateInstance()
    return current.format(Date())
}