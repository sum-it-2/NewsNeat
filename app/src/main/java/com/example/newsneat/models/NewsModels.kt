package com.example.newsneat.models

data class NewsModels(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)