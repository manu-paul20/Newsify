package com.manu.newsapplication.screens.newsDetailsScreen

import com.manu.newsapplication.newsReponseModel.Results

sealed interface DetailsScreenEvents{
    data class BookMarkNews(val results: Details): DetailsScreenEvents

    data class RemoveBookMark(val results: Details): DetailsScreenEvents

    data class SaveNews(val results: Details): DetailsScreenEvents
}