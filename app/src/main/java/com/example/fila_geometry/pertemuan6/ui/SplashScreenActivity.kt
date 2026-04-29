package com.example.fila_geometry.pertemuan6.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.fila_geometry.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import android.view.animation.AnimationUtils
import com.example.fila_geometry.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load and start animation
        val fadeInScale = AnimationUtils.loadAnimation(this, R.anim.fade_in_scale)
        binding.ivLogoSplash.startAnimation(fadeInScale)
        binding.tvAppName.startAnimation(fadeInScale)

        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        lifecycleScope.launch {
            delay(2000)
            val intent = if (isLogin) {
                Intent(this@SplashScreenActivity, MainActivity::class.java)
            } else {
                Intent(this@SplashScreenActivity, AuthActivity::class.java)
            }
            startActivity(intent)
            finish()
        }
    }
}