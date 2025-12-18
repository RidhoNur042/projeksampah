package com.ridho.project.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.ridho.project.database.DatabaseClient.Companion.getInstance
import com.ridho.project.database.dao.DatabaseDao
import com.ridho.project.model.ModelDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers


class InputDataViewModel(application: Application) : AndroidViewModel(application) {

    private val databaseDao: DatabaseDao =
        getInstance(application).appDatabase.databaseDao()

    fun addDataSampah(
        namaPengguna: String,
        jenisSampah: String,
        berat: Int,
        harga: Int,
        tanggal: String,
        alamat: String,
        catatan: String
    ) {

        val data = ModelDatabase(
            namaPengguna = namaPengguna,
            jenisSampah = jenisSampah,
            berat = berat,
            harga = harga,
            tanggal = tanggal,
            alamat = alamat,
            catatan = catatan,
            tipe = "Masuk",
            jumlah = harga,
            keterangan = "Setoran"
        )

        databaseDao.insertData(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    // INSERT BERHASIL
                    Log.d("DB_VM", "Insert berhasil")
                },
                { error ->
                    // INSERT GAGAL
                    Log.e("DB_VM", "Insert gagal", error)
                }
            )
    }
}
