package com.manu.newsapplication.retrofit.newsReponseModel

import com.manu.newsapplication.retrofit.newsReponseModel.Result

data class News_response(
    val nextPage: String,
    val results: List<Result>,
    val status: String,
    val totalResults: Int
)