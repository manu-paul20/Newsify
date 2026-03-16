package com.manu.newsapplication.screens.offlineNewsScreen

import com.manu.newsapplication.database.entities.OfflineNews
import com.manu.newsapplication.screens.newsDetailsScreen.Details

sealed interface OfflineNewsScreenEvents{
    object DeleteAllNews: OfflineNewsScreenEvents

    data class DeleteNews(val news: Details): OfflineNewsScreenEvents
}