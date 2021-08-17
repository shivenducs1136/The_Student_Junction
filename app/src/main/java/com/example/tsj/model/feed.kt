package com.example.tsj.model

data class feed(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)