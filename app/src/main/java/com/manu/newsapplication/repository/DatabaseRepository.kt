package com.manu.newsapplication.repository

import com.manu.newsapplication.database.NewsDataBase
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val db: NewsDataBase
) {
    fun getOfflineNewsDao() = db.offlineNewsDao
    fun getBookMarksDao() = db.bookMarksDao
}