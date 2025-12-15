package com.ridho.project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ridho.project.database.DatabaseClient.Companion.getInstance
import com.ridho.project.database.dao.DatabaseDao
import com.ridho.project.model.ModelDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers


class InputDataViewModel(application: Application) : AndroidViewModel(application) {

    var databaseDao: DatabaseDao?

    fun addDataSampah(
        nama_pengguna: String,
        jenis_sampah: String,
        berat: Int,
        harga: Int, // Ini adalah total uang yang diterima
        tanggal: String,
        alamat: String,
        catatan: String
    ) {
        Completable.fromAction {
            val modelDatabase = ModelDatabase()
            modelDatabase.namaPengguna = nama_pengguna
            modelDatabase.jenisSampah = jenis_sampah
            modelDatabase.berat = berat
            modelDatabase.harga = harga
            modelDatabase.tanggal = tanggal
            modelDatabase.tipe = "Masuk"
            modelDatabase.jumlah = harga
            modelDatabase.alamat = alamat
            modelDatabase.catatan = catatan


            databaseDao?.insertData(modelDatabase)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    init {
        databaseDao = getInstance(application)?.appDatabase?.databaseDao()
    }
}