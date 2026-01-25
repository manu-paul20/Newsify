package com.manu.newsapplication.screens.homeScreen

import com.manu.newsapplication.newsReponseModel.NewsResponse

sealed interface HomeScreenEvents {
    data class GetInitialNews(val searchQuery: String): HomeScreenEvents

    data class OnSearchQueryChange(val query: String): HomeScreenEvents

    data class ShowNewsDetails(val newsResponse: NewsResponse): HomeScreenEvents

    data class ChangeSuggestionChip(val chip: SuggestionChips): HomeScreenEvents

    object GetNextPage : HomeScreenEvents

}