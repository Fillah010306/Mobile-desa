package com.example.fila_geometry.pertemuan6.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.fila_geometry.R
import com.example.fila_geometry.databinding.ActivityInputDataBinding
import com.example.fila_geometry.utils.NotificationHelper
import com.example.fila_geometry.utils.PermissionHelper
import com.example.fila_geometry.utils.ReminderHelper
import java.util.Calendar

class InputDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputDataBinding
    private var serviceType: String = ""

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                processSubmission()
            } else {
                Toast.makeText(this, "Izin notifikasi diperlukan untuk pengingat status", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        serviceType = intent.getStringExtra("SERVICE_TYPE") ?: "Layanan"
        
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "Formulir $serviceType"
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.tvServiceTitle.text = serviceType
        when(serviceType) {
            "KTP Digital" -> binding.ivServiceIcon.setImageResource(R.drawable.ic_ktp)
            "Kartu Keluarga" -> binding.ivServiceIcon.setImageResource(R.drawable.ic_kk)
            "Akta Lahir" -> binding.ivServiceIcon.setImageResource(R.drawable.ic_akta)
            "Pindah Domisili" -> binding.ivServiceIcon.setImageResource(R.drawable.ic_pindah)
        }

        binding.btnSubmit.setOnClickListener {
            checkPermissionAndSubmit()
        }
    }

    private fun checkPermissionAndSubmit() {
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(notificationPermissionLauncher, permission)
            } else {
                processSubmission()
            }
        } else {
            processSubmission()
        }
    }

    private fun processSubmission() {
        val nik = binding.etNik.text.toString().trim()
        val nama = binding.etNama.text.toString().trim()

        if (nik.isEmpty() || nama.isEmpty()) {
            Toast.makeText(this, "Harap isi NIK dan Nama", Toast.LENGTH_SHORT).show()
            return
        }

        // 1. Simpan ke SharedPref
        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        sharedPref.edit().apply {
            putString("last_nik", nik)
            putString("last_nama", nama)
            putString("last_service", serviceType)
            putString("last_status", "Diproses")
            apply()
        }

        // 2. Notifikasi Langsung
        NotificationHelper.showNotification(
            this,
            "Pengajuan Terkirim",
            "Permohonan $serviceType Anda sedang kami proses.",
            Intent(this, MainActivity::class.java)
        )

        // 3. Set Reminder (Muncul 2 menit lagi)
        val calendar = Calendar.getInstance().apply {
            add(Calendar.MINUTE, 2) 
        }

        ReminderHelper.setReminder(
            context = this,
            hour = calendar.get(Calendar.HOUR_OF_DAY),
            minute = calendar.get(Calendar.MINUTE),
            title = "Cek Status Layanan",
            message = "Halo $nama, sudah 2 menit sejak pengajuan $serviceType. Cek status terbaru Anda sekarang!",
            targetActivity = MainActivity::class.java
        )

        Toast.makeText(this, "Berhasil! Pengingat diset untuk 2 menit lagi.", Toast.LENGTH_LONG).show()
        finish()
    }
}
