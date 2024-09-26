package com.example.spinnertiket

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spinnertiket.MainActivity.Companion
import com.example.spinnertiket.MainActivity.Companion.EXTRA_JAM
import com.example.spinnertiket.MainActivity.Companion.EXTRA_TANGGAL
import com.example.spinnertiket.MainActivity.Companion.EXTRA_TUJUAN
import com.example.spinnertiket.databinding.ActivityDialogconfirmBinding
import com.example.spinnertiket.databinding.ActivityMainBinding

class dialogconfirm : AppCompatActivity() {
    private lateinit var binding: ActivityDialogconfirmBinding
    companion object {
       const val EXTRA_NAME = "extra_name"
        const val EXTRA_JAM = "extra_jam"
        const val EXTRA_TANGGAL = "extra_tanggal"
        const val EXTRA_TUJUAN = "extra_tujuan"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogconfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnYes.setOnClickListener {
        val name = intent.getStringExtra(MainActivity.EXTRA_NAME)
        val jam_brngkt = intent.getStringExtra(MainActivity.EXTRA_JAM)
        val tgl_brngkt = intent.getStringExtra(MainActivity.EXTRA_TANGGAL)
        val tujuan = intent.getStringExtra(MainActivity.EXTRA_TUJUAN)
        val intentToSecondActivity = Intent(this@dialogconfirm, MainActivity2::class.java).apply {
            putExtra(EXTRA_NAME,name)
            putExtra(EXTRA_JAM,jam_brngkt)
            putExtra(EXTRA_TANGGAL,tgl_brngkt)
            putExtra(EXTRA_TUJUAN,tujuan)
        }
        startActivity(intentToSecondActivity)
        }
    }
}