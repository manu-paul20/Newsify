package com.manu.newsapplication.domain

import com.manu.newsapplication.database.dao.BookMarksDAO
import com.manu.newsapplication.database.dao.OfflineNewsDAO
import com.manu.newsapplication.newsReponseModel.NewsResponse
import retrofit2.Response


interface MyRepository {

    suspend fun getNews(
        query: String?,
        page: String?
    ): Response<NewsResponse>

    fun getBookMarksDAO(): BookMarksDAO
    fun getOfflineNewsDAO(): OfflineNewsDAO

}