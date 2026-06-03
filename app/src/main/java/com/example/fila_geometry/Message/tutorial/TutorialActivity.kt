package com.example.fila_geometry.Message.tutorial

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.fila_geometry.databinding.ActivityTutorialBinding
import com.example.fila_geometry.pertemuan6.ui.MainActivity

class TutorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TutorialAdapter(this)
        binding.viewPagerTutorial.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPagerTutorial)

        // Set initial visibility
        binding.btnNext.visibility = View.GONE

        binding.btnNext.setOnClickListener {
            val currentItem = binding.viewPagerTutorial.currentItem
            if (currentItem < 2) {
                binding.viewPagerTutorial.currentItem = currentItem + 1
            } else {
                // Selesai tutorial, pindah ke Home
                navigateToHome()
            }
        }

        binding.btnSkip.setOnClickListener {
            navigateToHome()
        }

        // Mengatur visibilitas tombol berdasarkan halaman
        binding.viewPagerTutorial.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.btnNext.visibility = View.VISIBLE
                    binding.btnNext.text = "Mulai Sekarang"
                    binding.btnSkip.visibility = View.GONE
                } else {
                    binding.btnNext.visibility = View.GONE
                    binding.btnSkip.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}