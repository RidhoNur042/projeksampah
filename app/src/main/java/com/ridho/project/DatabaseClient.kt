package com.ridho.project.database

import android.content.Context
import androidx.room.Room

class DatabaseClient private constructor(context: Context) {

    val appDatabase: AppDatabase

    init {
        appDatabase = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "keuangan_database" // Nama file database
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        @Volatile
        private var INSTANCE: DatabaseClient? = null

        fun getInstance(context: Context): DatabaseClient {
            return INSTANCE ?: synchronized(this) {
                val instance = DatabaseClient(context)
                INSTANCE = instance
                instance
            }
        }
    }
}