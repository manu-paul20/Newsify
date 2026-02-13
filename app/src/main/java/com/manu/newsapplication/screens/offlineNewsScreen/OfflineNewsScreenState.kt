package com.manu.newsapplication.screens.offlineNewsScreen

import com.manu.newsapplication.database.entities.OfflineNews

data class OfflineNewsScreenState(
    val offlineNews : List<OfflineNews> = emptyList(),
    val isLoading: Boolean = false
)
