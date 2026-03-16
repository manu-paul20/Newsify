package com.manu.newsapplication.screens.offlineNewsScreen

import com.manu.newsapplication.database.entities.OfflineNews
import com.manu.newsapplication.screens.newsDetailsScreen.Details

data class OfflineNewsScreenState(
    val offlineNews : List<Details> = emptyList(),
    val isLoading: Boolean = false
)
