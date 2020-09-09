package com.cha.estacionamiento.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "autos_table")
data class AddAutos (
    @PrimaryKey()
    @ColumnInfo(name = "placa_auto")
    val placa: String,
    @ColumnInfo(name = "tipo_auto")
    val tipoAuto: String,
    @ColumnInfo(name = "status")
    val statusAuto: String,
    @ColumnInfo(name = "hora")
    val horaIngreso: String,
    @ColumnInfo(name = "fecha_ingreso")
    val fechaIngreso: String,
    @ColumnInfo(name = "fecha_salida")
    val fechaSalida: String,
    @ColumnInfo(name = "tarifa_auto")
    val tarifaAuto: Double
)