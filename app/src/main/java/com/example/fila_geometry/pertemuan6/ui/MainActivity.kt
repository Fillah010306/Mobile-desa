package com.example.fila_geometry.pertemuan6.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fila_geometry.R
import com.example.fila_geometry.databinding.ActivityMainBinding
import com.example.fila_geometry.pertemuan6.fragments.HomeFragment
import com.example.fila_geometry.pertemuan6.fragments.ProfileFragment
import com.example.fila_geometry.pertemuan6.fragments.ServicesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Initial Fragment
        if (savedInstanceState == null) {
            loadFragment(HomeFragment(), "Beranda")
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment(), "Beranda")
                    true
                }
                R.id.nav_services -> {
                    loadFragment(ServicesFragment(), "Layanan")
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(ProfileFragment(), "Profil")
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment, title: String) {
        binding.tvLargeTitle.text = title
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}