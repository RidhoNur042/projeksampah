package com.ridho.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ridho.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setInitLayout()
    }

    private fun setInitLayout() {

        // Tombol Jemput Sampah → ke MapsActivity
        binding.contentMainLayout.cvInput.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        // Tombol Jenis Sampah → ke JenisSampahActivity
        binding.contentMainLayout.cvKategori.setOnClickListener {
            val intent = Intent(this@MainActivity, JenisSampahActivity::class.java)
            startActivity(intent)
        }

    }
}
