package com.manu.newsapplication.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookMarks(
    val content: String,
    val description: String,
    val image_url: String,
    val link: String,
    val pubDate: String,
    val source_icon: String,
    val source_name: String,
    val source_url: String,
    @PrimaryKey val title: String,
)