package com.manu.newsapplication.repository

import com.manu.newsapplication.constants.MyConstants
import com.manu.newsapplication.domain.MyRepository
import com.manu.newsapplication.retrofit.ApiRequests
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val apiRequests: ApiRequests
): MyRepository {
    override suspend fun getNews(query: String, page: String) {
        apiRequests.getNews(
            apiKey = MyConstants.API_KEY,
            query = query,
            page = page
        )
    }
}