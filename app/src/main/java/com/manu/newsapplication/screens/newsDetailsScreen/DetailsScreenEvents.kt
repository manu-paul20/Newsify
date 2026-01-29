package com.manu.newsapplication.screens.newsDetailsScreen

import com.manu.newsapplication.newsReponseModel.Results

sealed interface DetailsScreenEvents{
    data class BookMarkNews(val results: Results): DetailsScreenEvents

    data class RemoveBookMark(val results: Results): DetailsScreenEvents

    data class SaveNews(val results: Results): DetailsScreenEvents
}