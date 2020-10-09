package com.example.news.model


data class Response(
    var articles: List<Article>,
    val status: String,
    val totalResults: Int
)