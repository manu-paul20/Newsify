package com.manu.newsapplication.newsReponseModel

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val nextPage: String?,
    val results: List<Results>,
    val status: String?,
    val totalResults: String?
)