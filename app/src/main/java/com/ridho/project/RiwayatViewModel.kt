package com.ridho.project

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ridho.project.database.DatabaseClient
import com.ridho.project.database.dao.DatabaseDao
import com.ridho.project.model.ModelDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers


class RiwayatViewModel(application: Application) : AndroidViewModel(application) {

    val dataBank: LiveData<List<ModelDatabase>>
    val totalSaldo: LiveData<Int>

    private val databaseDao: DatabaseDao =
        DatabaseClient.getInstance(application)
            .appDatabase
            .databaseDao()

    init {
        dataBank = databaseDao.getAll()
        totalSaldo = databaseDao.getSaldo()
    }

    fun deleteDataById(uid: Int) {
        databaseDao.deleteSingleData(uid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("DB_VM", "Delete berhasil uid=$uid")
                },
                { error ->
                    Log.e("DB_VM", "Delete gagal uid=$uid", error)
                }
            )
    }
}
