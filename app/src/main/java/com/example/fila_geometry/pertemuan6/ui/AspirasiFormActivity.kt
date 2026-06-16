package com.example.fila_geometry.pertemuan6.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.fila_geometry.data.AppDatabase
import com.example.fila_geometry.data.entity.AspirasiEntity
import com.example.fila_geometry.databinding.ActivityAspirasiFormBinding
import kotlinx.coroutines.launch

class AspirasiFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAspirasiFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAspirasiFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnSaveAspirasi.setOnClickListener {
            val subjek = binding.etSubjek.text.toString()
            val pesan = binding.etPesan.text.toString()

            if (subjek.isNotBlank() && pesan.isNotBlank()) {
                lifecycleScope.launch {
                    val aspirasi = AspirasiEntity(
                        subjek = subjek,
                        pesan = pesan,
                        tanggal = System.currentTimeMillis()
                    )
                    db.aspirasiDao().insert(aspirasi)
                    Toast.makeText(this@AspirasiFormActivity, "Aspirasi berhasil dikirim", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
