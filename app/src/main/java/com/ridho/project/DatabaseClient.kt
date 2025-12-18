package com.ridho.project.database

import android.content.Context
import androidx.room.Room

class DatabaseClient private constructor(context: Context) {

    val appDatabase: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "keuangan_database"
    )
        // HAPUS INI SELAMA DEBUG
        // .fallbackToDestructiveMigration()
        .build()

    companion object {
        @Volatile
        private var INSTANCE: DatabaseClient? = null

        fun getInstance(context: Context): DatabaseClient =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: DatabaseClient(context).also { INSTANCE = it }
            }
    }
}
