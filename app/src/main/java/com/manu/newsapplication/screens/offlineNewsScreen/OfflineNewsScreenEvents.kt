package com.manu.newsapplication.screens.offlineNewsScreen

import com.manu.newsapplication.database.entities.OfflineNews

sealed interface OfflineNewsScreenEvents{
    object DeleteAllNews: OfflineNewsScreenEvents

    data class DeleteNews(val news: OfflineNews): OfflineNewsScreenEvents
}