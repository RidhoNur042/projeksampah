package com.ridho.project.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ridho.project.model.ModelDatabase
import io.reactivex.rxjava3.core.Completable // Import ini penting

@Dao
interface DatabaseDao {

    @Query("SELECT * FROM table_riwayat ORDER BY uid DESC")
    fun getAll(): LiveData<List<ModelDatabase>>

    @Query("SELECT SUM(CASE WHEN tipe = 'Masuk' THEN jumlah ELSE -jumlah END) FROM table_riwayat")
    fun getSaldo(): LiveData<Int>

    @Query("DELETE FROM table_riwayat WHERE uid = :uid")
    fun deleteSingleData(uid: Int): Completable

    @Insert
    fun insertData(modelDatabase: ModelDatabase): Completable
}