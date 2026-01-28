package com.manu.newsapplication.screens.newsDetailsScreen

import com.manu.newsapplication.newsReponseModel.News

sealed interface DetailsScreenEvents{
    data class BookMarkNews(val news: News): DetailsScreenEvents

    data class SaveNews(val news: News): DetailsScreenEvents
}