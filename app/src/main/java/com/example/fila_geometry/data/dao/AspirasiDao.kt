package com.example.fila_geometry.data.dao

import androidx.room.*
import com.example.fila_geometry.data.entity.AspirasiEntity

@Dao
interface AspirasiDao {
    @Query("SELECT * FROM aspirasi ORDER BY tanggal DESC")
    suspend fun getAll(): List<AspirasiEntity>

    @Insert
    suspend fun insert(aspirasi: AspirasiEntity)

    @Delete
    suspend fun delete(aspirasi: AspirasiEntity)
}
