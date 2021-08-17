package com.example.tsj.model

import com.google.gson.annotations.SerializedName

data class Feeds(
    @SerializedName("articles")
    val articles: List<Article>? = null,

    ){

    data class Article (
        val author: String? = null,
        val content: String? = null,
        val description: String? = null,
        val publishedAt: String? = null,
        val source: Source? = null,
        val title: String? = null,
        val url: String? = null,
        val urlToImage: String? = null
        )
}
