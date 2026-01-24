package com.manu.newsapplication.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.manu.newsapplication.database.entities.BookMarks
import com.manu.newsapplication.database.entities.OfflineNews
import kotlinx.coroutines.flow.Flow

@Dao
interface OfflineNewsDAO{
    @Query("SELECT * FROM OfflineNews")
    fun getBookMarks(): Flow<List<OfflineNews>>

    @Insert
    suspend fun addToBookMarks(offlineNews: OfflineNews)

    @Delete
    suspend fun deleteFromBookMarks(bookMarks: BookMark)

}