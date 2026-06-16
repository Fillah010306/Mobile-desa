package com.example.fila_geometry.data.api

import com.example.fila_geometry.data.model.NewsResponse
import retrofit2.http.GET

interface NewsApiService {
    @GET("antara/terbaru")
    suspend fun getNews(): NewsResponse
}
