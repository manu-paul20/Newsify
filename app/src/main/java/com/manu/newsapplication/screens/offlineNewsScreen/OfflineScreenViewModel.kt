package com.manu.newsapplication.screens.offlineNewsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manu.newsapplication.database.entities.BookMarks
import com.manu.newsapplication.database.entities.OfflineNews
import com.manu.newsapplication.repository.DatabaseRepository
import com.manu.newsapplication.screens.newsDetailsScreen.Details
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfflineScreenViewModel @Inject constructor(
    repository: DatabaseRepository
): ViewModel(){
    private val offlineNewsDAO = repository.getOfflineNewsDao()
    private val offlineNews = offlineNewsDAO.getOfflineNews().map {list->
        list.map {
            Details(
                title = it.title,
                source = it.source_name,
                pubDate = it.pubDate,
                imageUrl = "",
                description = it.description,
                sourceUrl = "",
                isBookMarked = false
            )
        }
    }

    private val _state = MutableStateFlow(OfflineNewsScreenState())

    val state = combine(offlineNews,_state){offlineNews,state->
        state.copy(
            offlineNews = offlineNews
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), OfflineNewsScreenState())

    fun onEvent(event: OfflineNewsScreenEvents){
        when(event) {
            is OfflineNewsScreenEvents.DeleteAllNews -> {
                _state.update { it.copy(isLoading = true) }
                viewModelScope.launch {
                    offlineNewsDAO.deleteAll()
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
            }
            is OfflineNewsScreenEvents.DeleteNews -> {
                val news = event.news.run {
                    OfflineNews(
                        title = title?:"",
                        description = description,
                        pubDate = pubDate,
                        source_name = source
                    )
                }
                viewModelScope.launch {
                    offlineNewsDAO.deleteFromOfflineNews(news)
                }
            }
        }
    }

}