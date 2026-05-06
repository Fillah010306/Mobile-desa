package com.example.fila_geometry.pertemuan6.ui

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fila_geometry.R
import com.example.fila_geometry.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        binding.llHeader.startAnimation(slideUp)

        binding.tvLogin.setOnClickListener {
            finish()
        }

        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()
            val nik = binding.etNik.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Reset errors
            binding.tilName.error = null
            binding.tilPhone.error = null
            binding.tilNik.error = null
            binding.tilUsername.error = null
            binding.tilPassword.error = null

            var isValid = true

            if (name.isEmpty()) {
                binding.tilName.error = "Nama tidak boleh kosong"
                isValid = false
            }
            if (phone.isEmpty()) {
                binding.tilPhone.error = "No. Handphone tidak boleh kosong"
                isValid = false
            }
            if (nik.isEmpty()) {
                binding.tilNik.error = "NIK tidak boleh kosong"
                isValid = false
            }
            if (username.isEmpty()) {
                binding.tilUsername.error = "Username tidak boleh kosong"
                isValid = false
            }
            if (password.isEmpty()) {
                binding.tilPassword.error = "Password tidak boleh kosong"
                isValid = false
            }

            if (isValid) {
                val intent = Intent(this, VerificationActivity::class.java)
                intent.putExtra("phone", phone)
                startActivity(intent)
            }
        }
    }
}