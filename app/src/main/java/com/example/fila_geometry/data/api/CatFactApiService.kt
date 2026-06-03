package com.example.fila_geometry.data.api

import com.example.fila_geometry.data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}