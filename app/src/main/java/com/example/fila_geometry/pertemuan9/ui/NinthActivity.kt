package com.example.fila_geometry.pertemuan9.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fila_geometry.R
import com.example.fila_geometry.databinding.ActivityNinthBinding
import com.example.fila_geometry.pertemuan6.fragments.ProfileFragment
import com.example.fila_geometry.pertemuan6.fragments.ServicesFragment
import com.example.fila_geometry.pertemuan9.fragment.HomeFragment

class NinthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNinthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupBottomNavigation()
        
        // Set default fragment
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_services -> ServicesFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> HomeFragment()
            }
            loadFragment(fragment)
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragmentContainer, fragment)
            .commit()
            
        // Optional: Update toolbar title based on fragment
        updateToolbarTitle(fragment)
    }

    private fun updateToolbarTitle(fragment: Fragment) {
        val title = when (fragment) {
            is HomeFragment -> getString(R.string.ninth_title)
            is ServicesFragment -> "Layanan"
            is ProfileFragment -> "Profil Saya"
            else -> getString(R.string.ninth_title)
        }
        binding.toolbar.title = title
    }
}