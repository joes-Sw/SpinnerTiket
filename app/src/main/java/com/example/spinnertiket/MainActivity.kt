package com.example.spinnertiket

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.spinnertiket.MainActivity.Companion.EXTRA_JAM
import com.example.spinnertiket.MainActivity.Companion.EXTRA_NAME
import com.example.spinnertiket.MainActivity.Companion.EXTRA_TANGGAL
import com.example.spinnertiket.MainActivity.Companion.EXTRA_TUJUAN
import com.example.spinnertiket.databinding.ActivityMainBinding
import com.example.spinnertiket.databinding.DialogConfirmBinding
import java.util.Calendar

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: ActivityMainBinding
    private val provinces = arrayOf(
        "Aceh",
        "Bali",
        "Banten",
        "Bengkulu",
        "Gorontalo",
        "Jakarta",
        "Jambi",
        "Jawa Barat",
        "Jawa Tengah",
        "Jawa Timur"
    )
    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_JAM = "extra_jam"
        const val EXTRA_TANGGAL = "extra_tanggal"
        const val EXTRA_TUJUAN = "extra_tujuan"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            val adapterProvinces = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_item, provinces
            )
            adapterProvinces.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )
            spinnerTujuan.setAdapter(adapterProvinces)

            btnShowCustomDialog.setOnClickListener {
                val nama = binding.etNamaPemesan.text.toString()
                val jam_brngkt = binding.etJamKeberangkatan.text.toString()
                val tgl_brngkt = binding.etTanggalKeberangkatan.text.toString()
                val tujuan = binding.spinnerTujuan.selectedItem.toString()
                val dialog = DialogConfirm(nama,jam_brngkt,tgl_brngkt,tujuan)

                dialog.show(supportFragmentManager, "dialogExit")

            }
            etTanggalKeberangkatan.setOnClickListener {
                val datePicker = DatePicker()
                datePicker.show(supportFragmentManager, "datePicker")
            }

            etJamKeberangkatan.setOnClickListener {
                val timePicker = TimePicker()
                timePicker.show(supportFragmentManager, "timePicker")
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val selectedDate = "$year/${month + 1}/$dayOfMonth"
        binding.etTanggalKeberangkatan.setText(selectedDate)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
        binding.etJamKeberangkatan.setText(selectedTime)
    }
}

class DatePicker: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val monthOfYear = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(
            requireActivity(),
            activity as DatePickerDialog.OnDateSetListener,
            year,
            monthOfYear,
            dayOfMonth
        )
    }
}

class TimePicker: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(
            requireActivity(),
            activity as TimePickerDialog.OnTimeSetListener,
            hourOfDay,minute,
            DateFormat.is24HourFormat(activity)
        )
    }
}


class DialogConfirm(
    private val username: String?,
    private val jam_brngkt: String?,
    private val tgl_brngkt: String?,
    private val tujuan: String?) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val binding = DialogConfirmBinding.inflate(inflater)
        with(binding) {
            btnYes.setOnClickListener {
                val intent = Intent(requireActivity(), MainActivity2::class.java).apply {
                    putExtra(EXTRA_NAME, username)
                    putExtra(EXTRA_JAM, jam_brngkt)
                    putExtra(EXTRA_TANGGAL, tgl_brngkt)
                    putExtra(EXTRA_TUJUAN, tujuan)
                }
                startActivity(intent)
            }
            btnNo.setOnClickListener {
                dismiss()
            }
        }
        builder.setView(binding.root)
        return builder.create()
    }

}