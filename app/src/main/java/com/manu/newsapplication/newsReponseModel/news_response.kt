package com.manu.newsapplication.newsReponseModel

data class newsResponse(
    val nextPage: String,
    val results: List<Result>,
    val status: String,
    val totalResults: Int
)