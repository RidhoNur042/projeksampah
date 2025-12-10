package com.ridho.project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

// Import model dan repository Anda di sini
// import com.ridho.project.model.ModelSampah
// import com.ridho.project.repository.SampahRepository

class InputDataViewModel(application: Application) : AndroidViewModel(application) {

    // Asumsi: Anda memiliki Repository dan Room Database
    // private val sampahRepository: SampahRepository

    init {
        // Asumsi: Inisialisasi Database dan Repository
        // val sampahDao = DatabaseClient.getInstance(application).appDatabase.sampahDao()
        // sampahRepository = SampahRepository(sampahDao)
    }

    // Fungsi yang dipanggil dari Activity
    fun addDataSampah(
        nama: String,
        kategori: String,
        berat: Int,
        hargaTotal: Int,
        tanggal: String,
        alamat: String,
        catatan: String
    ) {
        // Lakukan operasi penyimpanan data ke database di sini
        // Biasanya menggunakan Kotlin Coroutines (viewModelScope.launch)

        /*
        viewModelScope.launch(Dispatchers.IO) {
            val dataSampah = ModelSampah(
                nama = nama,
                kategori = kategori,
                berat = berat,
                hargaTotal = hargaTotal,
                tanggal = tanggal,
                alamat = alamat,
                catatan = catatan
            )
            sampahRepository.insert(dataSampah)
        }
        */

        // Karena kita tidak memiliki Model dan Repository, biarkan kosong sementara
    }
}