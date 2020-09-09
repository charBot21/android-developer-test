package com.cha.estacionamiento.ui.utils

import java.text.SimpleDateFormat
import java.util.*


fun getHour(): String {
    val current = SimpleDateFormat("hh:mm")
    val currentHour = current.format(Date())

    return currentHour
}

fun getDateEnter(): String {
    val current = SimpleDateFormat("dd/M/yyyy")
    val currentDate = current.format(Date())

    return currentDate
}