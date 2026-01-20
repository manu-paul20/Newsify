package com.manu.newsapplication.domain


interface MyRepository {

    suspend fun getNews(
        query: String,
        page: String
    )
}