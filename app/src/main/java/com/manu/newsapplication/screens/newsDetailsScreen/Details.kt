package com.manu.newsapplication.screens.newsDetailsScreen

import kotlinx.serialization.Serializable

@Serializable
data class Details(
    val imageUrl: String = "",
    val title: String = "",
    val description: String = "",
    val pubDate: String = "",
    val source: String = "",
    val sourceUrl: String = "",
    val isBookMarked: Boolean = false
)
