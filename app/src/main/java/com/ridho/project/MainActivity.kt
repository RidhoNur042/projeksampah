package com.ridho.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ridho.project.databinding.ActivityMainBinding
import com.ridho.project.databinding.ContentMainBinding
// Hapus import yang tidak terpakai jika Anda tidak menggunakannya di MainActivity:
// import android.content.pm.PackageManager
// import android.graphics.Color
// import android.location.Geocoder
// import android.os.Build

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Panggil fungsi inisialisasi listener
        setInitLayout()

    }

    private fun setInitLayout(){

        binding.tvCurrentLocation.setOnClickListener { v: View? ->
            val intent = Intent(this@MainActivity, MapsActivity::class.java)
            startActivity(intent)
        }
        binding.contentMainLayout.cvInput.setOnClickListener { v: View? ->
                val intent = Intent(this@MainActivity, InputDataActivity::class.java)
                startActivity(intent)
            }
    }
}