package com.manu.newsapplication.newsReponseModel

data class NewsResponse(
    val nextPage: String,
    val results: List<Result>,
    val status: String,
    val totalResults: Int
)