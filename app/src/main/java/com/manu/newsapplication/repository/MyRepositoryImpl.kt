package com.manu.newsapplication.repository

import com.manu.newsapplication.constants.MyConstants
import com.manu.newsapplication.database.NewsDataBase
import com.manu.newsapplication.database.dao.BookMarksDAO
import com.manu.newsapplication.database.dao.OfflineNewsDAO
import com.manu.newsapplication.domain.MyRepository
import com.manu.newsapplication.newsReponseModel.NewsResponse
import com.manu.newsapplication.retrofit.ApiRequests
import okhttp3.Response
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val apiRequests: ApiRequests,
    private val db: NewsDataBase
): MyRepository {
    override suspend fun getNews(query: String?, page: String?) : retrofit2.Response<NewsResponse>{
        return apiRequests.getNews(
            apiKey = MyConstants.API_KEY,
            query = query,
            page = page
        )
    }

    override fun getBookMarksDAO(): BookMarksDAO = db.bookMarksDao

    override fun getOfflineNewsDAO(): OfflineNewsDAO = db.offlineNewsDao
}