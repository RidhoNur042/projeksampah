package com.ridho.project

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ridho.project.database.DatabaseClient
import com.ridho.project.database.dao.DatabaseDao
import com.ridho.project.model.ModelDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers


class RiwayatViewModel(application: Application) : AndroidViewModel(application) {

    var totalSaldo: LiveData<Int>
    var dataBank: LiveData<List<ModelDatabase>>
    private val databaseDao: DatabaseDao

    init {
        // --- PERBAIKAN 1: Menggunakan package proyek Anda ---
        // Asumsi: DatabaseClient Anda memiliki metode getInstance().
        databaseDao = DatabaseClient.getInstance(application)
            .appDatabase
            .databaseDao()

        dataBank = databaseDao.getAll()
        totalSaldo = databaseDao.getSaldo()
    }

    // Fungsi deleteDataById tetap menggunakan RxJava untuk operasi I/O eksplisit
    fun deleteDataById(uid: Int) {
        Completable.fromAction {
            // Pemanggilan aman tanpa tanda tanya, karena databaseDao sudah dijamin tidak null di init
            databaseDao.deleteSingleData(uid)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}