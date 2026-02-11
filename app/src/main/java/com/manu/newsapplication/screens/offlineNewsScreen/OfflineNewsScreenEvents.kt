package com.manu.newsapplication.screens.offlineNewsScreen

import com.manu.newsapplication.database.entities.OfflineNews

sealed interface OfflineNewsScreenEvents{
    object DeleteSelected: OfflineNewsScreenEvents

    data class SelectNews(val news: OfflineNews): OfflineNewsScreenEvents

    object updateInitialState: OfflineNewsScreenEvents
}