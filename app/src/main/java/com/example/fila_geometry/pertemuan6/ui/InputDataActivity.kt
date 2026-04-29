package com.example.fila_geometry.pertemuan6.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fila_geometry.R
import com.example.fila_geometry.databinding.ActivityInputDataBinding

class InputDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputDataBinding
    private var serviceType: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        serviceType = intent.getStringExtra("SERVICE_TYPE") ?: "Layanan"
        
        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false) // Handle manual back via binding.toolbar
        binding.toolbar.title = "Formulir $serviceType"
        binding.toolbar.setNavigationOnClickListener { finish() }

        // Setup UI based on Service
        binding.tvServiceTitle.text = serviceType
        when(serviceType) {
            "KTP Digital" -> {
                binding.ivServiceIcon.setImageResource(R.drawable.ic_ktp)
                binding.tvServiceDesc.text = "Pengajuan identitas kependudukan digital baru"
            }
            "Kartu Keluarga" -> {
                binding.ivServiceIcon.setImageResource(R.drawable.ic_kk)
                binding.tvServiceDesc.text = "Pembaruan atau pembuatan KK baru"
            }
            "Akta Lahir" -> {
                binding.ivServiceIcon.setImageResource(R.drawable.ic_akta)
                binding.tvServiceDesc.text = "Pencatatan kelahiran warga baru"
            }
            "Pindah Domisili" -> {
                binding.ivServiceIcon.setImageResource(R.drawable.ic_pindah)
                binding.tvServiceDesc.text = "Pengurusan surat pindah antar daerah"
            }
        }

        binding.btnSubmit.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val nik = binding.etNik.text.toString().trim()
        val nama = binding.etNama.text.toString().trim()

        if (nik.isEmpty() || nama.isEmpty()) {
            Toast.makeText(this, "Harap isi NIK dan Nama", Toast.LENGTH_SHORT).show()
            return
        }

        if (nik.length < 16) {
            Toast.makeText(this, "NIK harus 16 digit", Toast.LENGTH_SHORT).show()
            return
        }

        // Save data to SharedPreferences to display on Dashboard
        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("last_nik", nik)
        editor.putString("last_nama", nama)
        editor.putString("last_service", serviceType)
        editor.putString("last_status", "Diproses")
        editor.apply()

        Toast.makeText(this, "Pengajuan $serviceType Berhasil Dikirim", Toast.LENGTH_LONG).show()
        finish()
    }
}