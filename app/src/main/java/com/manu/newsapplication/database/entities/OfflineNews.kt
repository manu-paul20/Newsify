package com.manu.newsapplication.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OfflineNews(
    val description: String,
    val pubDate: String,
    val source_name: String,
    val isSelected: Boolean = false,
    @PrimaryKey val title: String,
)

