package com.example.fila_geometry.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fila_geometry.data.dao.NoteDao
import com.example.fila_geometry.data.dao.AspirasiDao
import com.example.fila_geometry.data.entity.NoteEntity
import com.example.fila_geometry.data.entity.AspirasiEntity

@Database(
    entities = [NoteEntity::class, AspirasiEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun aspirasiDao(): AspirasiDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                .fallbackToDestructiveMigration()
                .build().also { INSTANCE = it }
            }
        }
    }
}
