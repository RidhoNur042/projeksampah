package com.ridho.project.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ridho.project.database.dao.DatabaseDao
import com.ridho.project.model.ModelDatabase

// Versi database harus di-increment jika ada perubahan skema
@Database(entities = [ModelDatabase::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao
}