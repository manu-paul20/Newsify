package com.manu.newsapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manu.newsapplication.database.dao.BookMarksDAO
import com.manu.newsapplication.database.dao.OfflineNewsDAO
import com.manu.newsapplication.database.entities.BookMarks
import com.manu.newsapplication.database.entities.OfflineNews

@Database(
    entities = [BookMarks::class, OfflineNews::class],
    version = 1
)
abstract class NewsDataBase: RoomDatabase() {
    abstract val bookMarksDao: BookMarksDAO
    abstract val offlineNewsDao: OfflineNewsDAO
}