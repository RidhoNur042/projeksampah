package com.ridho.project.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_riwayat")
data class ModelDatabase(

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,

    val namaPengguna: String,
    val jenisSampah: String,
    val berat: Int,
    val harga: Int,
    val tanggal: String,
    val alamat: String,
    val catatan: String,

    val tipe: String,      // "Masuk" / "Keluar"
    val jumlah: Int,
    val keterangan: String
)