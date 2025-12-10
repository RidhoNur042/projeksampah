package com.ridho.project.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

// Extension Function untuk format Rupiah
object FunctionHelper {

    // Fungsi untuk mengkonversi nilai Int ke format mata uang Rupiah
    fun rupiahFormat(number: Int): String {
        val localeID = Locale("in", "ID")
        val formatRupiah: DecimalFormat = DecimalFormat("Rp ###,###,###", DecimalFormatSymbols(localeID))

        // return formatRupiah.format(number) // Cara umum

        // Versi yang lebih sesuai untuk penggunaan di EditText/TextView agar tidak terlalu banyak simbol
        // Kami mengasumsikan Anda hanya ingin angka yang diformat
        val symbols = DecimalFormatSymbols(localeID)
        symbols.groupingSeparator = '.'
        symbols.monetaryDecimalSeparator = ','

        // Menggunakan format yang lebih sederhana tanpa simbol mata uang
        val decimalFormat = DecimalFormat("#,###", symbols)
        return decimalFormat.format(number)
    }
}