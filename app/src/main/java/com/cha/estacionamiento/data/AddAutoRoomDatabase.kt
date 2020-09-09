package com.cha.estacionamiento.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cha.estacionamiento.data.local.dao.AddAutoDao
import com.cha.estacionamiento.data.local.entity.AddAutos

@Database(entities = [AddAutos::class], version = 1)
abstract class AddAutoRoomDatabase: RoomDatabase() {

    abstract fun addAutosDao(): AddAutoDao

    companion object {
        @Volatile
        private var INSTANCE: AddAutoRoomDatabase ?= null

        fun getDatabase(context: Context): AddAutoRoomDatabase {
            val tmpInstance = INSTANCE

            if ( tmpInstance != null ) {
                return tmpInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AddAutoRoomDatabase::class.java,
                    "autos_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}