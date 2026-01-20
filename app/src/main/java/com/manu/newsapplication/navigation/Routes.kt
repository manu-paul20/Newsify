package com.manu.newsapplication.navigation

import androidx.navigation3.runtime.NavKey
import com.manu.newsapplication.newsReponseModel.newsResponse
import kotlinx.serialization.Serializable

@Serializable
interface Routes : NavKey {

    @Serializable
    data object HomeScreen : Routes

    @Serializable
    data object BookMarksScreen : Routes

    @Serializable
    data object OfflineNewsScreen : Routes

    @Serializable
    data class NewsDetailsScreen(val news: newsResponse): Routes

}