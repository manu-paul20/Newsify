package com.manu.newsapplication.screens.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manu.newsapplication.constants.MyConstants
import com.manu.newsapplication.retrofit.ApiRequests
import com.manu.newsapplication.retrofit.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val api: ApiRequests
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenStates())
    private val _suggestion = MutableStateFlow(SuggestionChips.All)
    val state = combine(_state, _suggestion) { state, suggestion ->
        state.copy(
            currentChip = suggestion
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeScreenStates())

    fun onEvent(event: HomeScreenEvents) {
        when (event) {
            is HomeScreenEvents.GetInitialNews -> {
                _state.update {
                    it.copy(
                        initialResponseStatus = NetworkResponse.Loading
                    )
                }
                    viewModelScope.launch {
                        try {
                            val response = api.getNews(
                                apiKey = MyConstants.API_KEY,
                                query = event.searchQuery,
                                page = null
                            )
                            if (response.isSuccessful && response.body() != null) {
                                val results = response.body()?.results ?: emptyList()
                                _state.update { st ->
                                        st.copy(
                                            initialResponseStatus = NetworkResponse.Success,
                                            resultsList = results.distinctBy { it.title }.filter {
                                                it.language?.lowercase() == "english"
                                                                                              },
                                            nextPage = response.body()!!.nextPage,
                                            isShowingFailurePopup = false
                                        )
                                }
                            } else {
                                _state.update {
                                    it.copy(
                                        errorMessage = "Seems like something went wrong\nTry again after some times",
                                        isShowingFailurePopup = true,
                                        initialResponseStatus = NetworkResponse.Failure(response.message())
                                    )
                                }

                            }
                        } catch (e: Exception) {
                            _state.update {
                                it.copy(
                                    errorMessage = "No Internet Connection",
                                    isShowingFailurePopup = true,
                                    initialResponseStatus = NetworkResponse.Failure("Something went wrong")
                                )
                            }
                        }
                    }
            }

            is HomeScreenEvents.OnSearchQueryChange -> {
                _state.update {
                    it.copy(
                        searchQuery = event.query
                    )
                }
            }

            is HomeScreenEvents.ChangeSuggestionChip -> {
                _suggestion.value = event.chip
                onEvent(HomeScreenEvents.GetInitialNews(event.chip.name))

            }

            HomeScreenEvents.GetNextPage -> {
                _state.update {
                    it.copy(newPageResponseStaus = NetworkResponse.Loading)
                }
                    viewModelScope.launch {
                        try {
                            val response = api.getNews(
                                apiKey = MyConstants.API_KEY,
                                query = null,
                                page = _state.value.nextPage
                            )
                            if (response.isSuccessful && response.body() != null) {
                                val results = response.body()!!.results
                                _state.update { st ->
                                    st.copy(
                                        newPageResponseStaus = NetworkResponse.Success,
                                        resultsList = (_state.value.resultsList + results.filter {
                                            it.language!!.lowercase() == "english"
                                        }).distinctBy {
                                            it.title
                                        },
                                        nextPage = response.body()!!.nextPage
                                    )
                                }
                            } else {
                                _state.update {
                                    it.copy(
                                        newPageResponseStaus = NetworkResponse.Failure(response.message())
                                    )
                                }

                            }
                        } catch (e: Exception) {
                            _state.update {
                                it.copy(
                                    newPageResponseStaus = NetworkResponse.Failure("Something went wrong")
                                )
                            }
                        }
                    }
            }

            is HomeScreenEvents.ShowFailurePopUp -> {
                _state.update {
                    it.copy(
                        isShowingFailurePopup = true
                    )
                }
            }

            is HomeScreenEvents.HideFailurePopUp -> {
                _state.update {
                    it.copy(
                        isShowingFailurePopup = false
                    )
                }
            }


        }
    }


}