package com.manu.newsapplication.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.manu.newsapplication.database.entities.BookMarks
import com.manu.newsapplication.database.entities.OfflineNews
import kotlinx.coroutines.flow.Flow

@Dao
interface BookMarksDAO{
    @Query("SELECT * FROM BookMarks")
    fun getBookMarks(): Flow<List<BookMarks>>

    @Upsert
    suspend fun addToBookMarks(bookMark: BookMarks)

    @Delete
    suspend fun deleteFromBookMarks(bookMark: BookMarks)

}