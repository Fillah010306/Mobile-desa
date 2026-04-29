package com.example.fila_geometry.pertemuan6.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.AnimationUtils
import com.example.fila_geometry.R
import com.example.fila_geometry.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Slide Up Animation for the card
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        binding.cardLogin.startAnimation(slideUp)
        binding.llIdentity.startAnimation(slideUp)

        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Reset Errors
            binding.tilUsername.error = null
            binding.tilPassword.error = null

            if (username.isEmpty() || password.isEmpty()) {
                if (username.isEmpty()) binding.tilUsername.error = "NIK / Username tidak boleh kosong"
                if (password.isEmpty()) binding.tilPassword.error = "Password tidak boleh kosong"
            } else {
                // Login Berhasil dengan inputan user apa saja (Validasi: Tidak Kosong)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}