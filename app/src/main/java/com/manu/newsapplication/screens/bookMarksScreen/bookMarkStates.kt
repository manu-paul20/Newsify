package com.manu.newsapplication.screens.bookMarksScreen

import com.manu.newsapplication.database.entities.BookMarks

data class BookMarkStates(
    val bookMarks: List<BookMarks> = emptyList()
)