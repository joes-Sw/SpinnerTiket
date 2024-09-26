package com.example.spinnertiket

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spinnertiket.databinding.ActivityMain2Binding
import com.example.spinnertiket.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(MainActivity.EXTRA_NAME)
        val jam_brngkt = intent.getStringExtra(MainActivity.EXTRA_JAM)
        val tgl_brngkt = intent.getStringExtra(MainActivity.EXTRA_TANGGAL)
        val tujuan = intent.getStringExtra(MainActivity.EXTRA_TUJUAN)

        with(binding) {
            txtHasilNama.text = ":   $name"
            txtHasilJam.text = ":   $jam_brngkt"
            txtHasilTanggal.text = ":   $tgl_brngkt"
            txtHasilTujuan.text = ":   $tujuan"
        }
    }
}