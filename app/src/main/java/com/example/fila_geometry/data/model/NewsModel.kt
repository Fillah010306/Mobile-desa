package com.example.fila_geometry.data.model

data class NewsResponse(
    val success: Boolean,
    val message: String?,
    val data: NewsData
)

data class NewsData(
    val link: String,
    val description: String,
    val title: String,
    val image: String,
    val posts: List<NewsPost>
)

data class NewsPost(
    val link: String,
    val title: String,
    val pubDate: String,
    val description: String,
    val thumbnail: String
)
