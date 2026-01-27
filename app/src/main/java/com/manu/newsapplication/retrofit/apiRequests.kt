package com.manu.newsapplication.retrofit

import com.manu.newsapplication.newsReponseModel.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiRequests {

    @GET("/api/1/latest")
    suspend fun getNews(
        @Query("apikey") apiKey: String,
        @Query("q") query: String?,
        @Query("page") page: String?
    ): Response<NewsResponse>
}