package com.manu.newsapplication.database.entities

import androidx.room.Entity

@Entity
data class OfflineNews(
    val content: String,
    val description: String,
    val link: String,
    val pubDate: String,
    val source_name: String,
    val source_url: String,
    val title: String,
)

