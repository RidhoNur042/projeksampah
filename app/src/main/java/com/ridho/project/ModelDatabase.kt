package com.ridho.project.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_riwayat")
data class ModelDatabase(
    // Kunci Utama
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0,

    // Properti yang dibutuhkan RiwayatAdapter:
    var namaPengguna: String? = null,
    var jenisSampah: String? = null,
    var berat: Int = 0, // Gunakan Double karena perbandingan di adapter
    var harga: Int = 0, // Pendapatan
    var tanggal: String? = null,
    var alamat : String? = null,
    var catatan : String? = null,

    // Properti tambahan (tipe dan jumlah, mungkin untuk saldo)
    var tipe: String? = null, // "Masuk" atau "Keluar" (Implied by ViewModel)
    var jumlah: Int = 0, // Saldo (Implied by ViewModel)
    var keterangan: String? = null
)