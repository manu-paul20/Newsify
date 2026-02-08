package com.manu.newsapplication.screens.bookMarksScreen

import com.manu.newsapplication.database.entities.BookMarks

sealed interface BookMarkEvents{
    data class DeleteBookMark(val news: BookMarks): BookMarkEvents
}