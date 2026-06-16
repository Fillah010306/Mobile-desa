package com.example.fila_geometry.pertemuan6.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.viewpager2.widget.ViewPager2
import com.example.fila_geometry.R
import com.example.fila_geometry.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf(
            OnBoardingItem(
                "Selamat Datang",
                "Aplikasi Layanan Desa untuk mempermudah pengurusan administrasi Anda.",
                android.R.drawable.ic_menu_info_details
            ),
            OnBoardingItem(
                "Pengajuan Cepat",
                "Ajukan KTP, KK, dan Akta Lahir langsung dari genggaman tangan Anda.",
                android.R.drawable.ic_menu_edit
            ),
            OnBoardingItem(
                "Informasi Terkini",
                "Dapatkan berita dan wawasan terbaru seputar desa kita tercinta.",
                android.R.drawable.ic_menu_myplaces
            )
        )

        val adapter = OnBoardingAdapter(items)
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == items.size - 1) {
                    binding.btnNext.text = "Ayo Mulai"
                } else {
                    binding.btnNext.text = "Selanjutnya"
                }
            }
        })

        binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem < items.size - 1) {
                binding.viewPager.currentItem += 1
            } else {
                completeOnboarding()
            }
        }
    }

    private fun completeOnboarding() {
        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        sharedPref.edit {
            putBoolean("onboarding_complete", true)
        }

        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}
