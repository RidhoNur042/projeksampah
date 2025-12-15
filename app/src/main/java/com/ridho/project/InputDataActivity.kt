package com.ridho.project

import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ridho.project.databinding.ActivityInputDataBinding
import com.ridho.project.utils.FunctionHelper.rupiahFormat
import com.ridho.project.viewmodel.InputDataViewModel
import java.text.SimpleDateFormat
import java.util.*

class InputDataActivity : AppCompatActivity() {

    // 1. Deklarasi View Binding
    private lateinit var binding: ActivityInputDataBinding

    lateinit var inputDataViewModel: InputDataViewModel

    lateinit var strNama: String
    lateinit var strTanggal: String
    lateinit var strAlamat: String
    lateinit var strCatatan: String
    lateinit var strKategoriSelected: String
    lateinit var strHargaSelected: String

    lateinit var strKategori: Array<String>
    lateinit var strHarga: Array<String>

    var countTotal = 0
    var countBerat = 0
    var countHarga = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Inisialisasi View Binding dan Set ContentView
        binding = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBar()
        setToolbar()
        setInitLayout()
        setInputData()
    }

    private fun setToolbar() {
        // Gunakan binding untuk mengakses Toolbar
        setSupportActionBar(binding.toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    private fun setInitLayout() {
        strKategori = resources.getStringArray(R.array.kategori_sampah)
        strHarga = resources.getStringArray(R.array.harga_perkilo)

        inputDataViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(InputDataViewModel::class.java)

        val arrayBahasa = ArrayAdapter(this@InputDataActivity, android.R.layout.simple_list_item_1, strKategori)
        arrayBahasa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spKategori.adapter = arrayBahasa

        binding.spKategori.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                strKategoriSelected = parent.getItemAtPosition(position).toString()
                strHargaSelected = strHarga[position]
                countHarga = try {
                    strHargaSelected.toInt()
                } catch (e: NumberFormatException) {
                    0 // Berikan nilai default jika gagal diubah ke Int
                }

                // Gunakan binding untuk inputBerat
                val beratText = binding.inputBerat.text.toString()
                if (beratText.isNotEmpty()) {
                    countBerat = beratText.toInt()
                    setTotalPrice(countBerat)
                } else {
                    // Gunakan binding untuk inputHarga
                    binding.inputHarga.setText(rupiahFormat(countHarga))
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        binding.inputBerat.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(editable: Editable) {
                // Gunakan binding
                binding.inputBerat.removeTextChangedListener(this)

                if (editable.isNotEmpty()) {
                    countBerat = editable.toString().toInt()
                    setTotalPrice(countBerat)
                } else {
                    // Reset berat ke 0 jika input kosong
                    countBerat = 0
                    setTotalPrice(countBerat)
                }
                binding.inputBerat.addTextChangedListener(this)
            }
        })

        binding.inputTanggal.setOnClickListener { view: View? ->
            val tanggalJemput = Calendar.getInstance()
            val date = OnDateSetListener { view1: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                tanggalJemput[Calendar.YEAR] = year
                tanggalJemput[Calendar.MONTH] = monthOfYear
                tanggalJemput[Calendar.DAY_OF_MONTH] = dayOfMonth
                val strFormatDefault = "d MMMM yyyy"
                val simpleDateFormat = SimpleDateFormat(strFormatDefault, Locale.getDefault())
                // Gunakan binding
                binding.inputTanggal.setText(simpleDateFormat.format(tanggalJemput.time))
            }
            DatePickerDialog(
                this@InputDataActivity, date,
                tanggalJemput[Calendar.YEAR],
                tanggalJemput[Calendar.MONTH],
                tanggalJemput[Calendar.DAY_OF_MONTH]
            ).show()
        }
    }

    private fun setTotalPrice(berat: Int) {
        countTotal = countHarga * berat
        // Gunakan binding untuk inputHarga
        binding.inputHarga.setText(rupiahFormat(countTotal))
    }

    private fun setInputData() {
        binding.btnCheckout.setOnClickListener { v: View? ->
            strNama = binding.inputNama.text.toString().trim()
            strTanggal = binding.inputTanggal.text.toString().trim()
            strAlamat = binding.inputAlamat.text.toString().trim()
            strCatatan = binding.inputTambahan.text.toString().trim()


            val isKategoriSelectedValid = ::strKategoriSelected.isInitialized && strKategoriSelected.isNotEmpty()

            if (strNama.isEmpty() || strTanggal.isEmpty() || strAlamat.isEmpty() || !isKategoriSelectedValid || (countBerat == 0) || (countTotal == 0)) {
                Toast.makeText(
                    this@InputDataActivity,
                    "Data tidak boleh ada yang kosong! (Pastikan berat dan kategori sudah dipilih)",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                inputDataViewModel.addDataSampah(
                    strNama,
                    strKategoriSelected,
                    countBerat,
                    countTotal,
                    strTanggal,
                    strAlamat,
                    strCatatan
                )
                Toast.makeText(
                    this@InputDataActivity,
                    "Pesanan Anda sedang diproses, cek di menu riwayat",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }
}