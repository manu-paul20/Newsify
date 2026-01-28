package com.manu.newsapplication.screens.homeScreen

import com.manu.newsapplication.newsReponseModel.News
import com.manu.newsapplication.retrofit.NetworkResponse

data class HomeScreenStates(
    val initialResponseStatus: NetworkResponse = NetworkResponse.Loading,
    val newPageResponseStaus: NetworkResponse = NetworkResponse.Success,
    val nextPage: String? = null,
    val currentChip: SuggestionChips = SuggestionChips.All,
    val newsList : List<News> = emptyList(),
    val searchQuery : String = "",
    val isShowingFailurePopup: Boolean = false,
    val errorMessage: String = ""
)