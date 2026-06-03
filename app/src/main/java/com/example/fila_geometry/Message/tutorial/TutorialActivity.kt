package com.example.fila_geometry.Message.tutorial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.fila_geometry.databinding.ActivityTutorialBinding
import com.example.fila_geometry.pertemuan6.ui.AuthActivity

class TutorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TutorialAdapter(this)
        binding.viewPagerTutorial.adapter = adapter

        binding.btnNext.setOnClickListener {
            val currentItem = binding.viewPagerTutorial.currentItem
            if (currentItem < 2) {
                binding.viewPagerTutorial.currentItem = currentItem + 1
            } else {
                // Selesai tutorial, pindah ke Auth/Main
                navigateToAuth()
            }
        }

        binding.btnSkip.setOnClickListener {
            navigateToAuth()
        }

        // Mengubah teks tombol pada halaman terakhir
        binding.viewPagerTutorial.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.btnNext.text = "Mulai Sekarang"
                } else {
                    binding.btnNext.text = "Lanjut"
                }
            }
        })
    }

    private fun navigateToAuth() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}