package com.cha.estacionamiento.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cha.estacionamiento.data.local.entity.AddAutos

@Dao
interface AddAutoDao {
    @Query( "SELECT * from autos_table")
    fun getAutos(): LiveData<List<AddAutos>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAuto(addAutos: AddAutos)
}