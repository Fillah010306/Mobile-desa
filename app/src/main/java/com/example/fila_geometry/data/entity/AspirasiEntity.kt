package com.example.fila_geometry.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aspirasi")
data class AspirasiEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val subjek: String,
    val pesan: String,
    val tanggal: Long
)
