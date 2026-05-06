package com.example.fila_geometry.pertemuan6.ui

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fila_geometry.R
import com.example.fila_geometry.databinding.ActivityVerificationBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class VerificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val phone = intent.getStringExtra("phone") ?: ""
        binding.tvVerificationDesc.text = "Kode OTP telah dikirim ke nomor $phone"

        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        binding.llHeader.startAnimation(slideUp)

        binding.btnVerify.setOnClickListener {
            val otp = binding.etOtp.text.toString().trim()
            
            if (otp.isEmpty()) {
                showErrorDialog("Kode OTP tidak boleh kosong")
            } else if (otp == phone) {
                Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()
                // Kembali ke Login (AuthActivity) sesuai alur atau lanjut ke Soal 3
                val intent = Intent(this, AuthActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
                finish()
            } else {
                showErrorDialog("Kode OTP salah! Harus sama dengan nomor handphone.")
            }
        }

        binding.tvResend.setOnClickListener {
            Toast.makeText(this, "Kode OTP telah dikirim ulang", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Kesalahan Verifikasi")
            .setMessage(message)
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}