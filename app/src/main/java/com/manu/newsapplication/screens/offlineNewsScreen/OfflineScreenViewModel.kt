package com.manu.newsapplication.screens.offlineNewsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manu.newsapplication.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfflineScreenViewModel @Inject constructor(
    repository: DatabaseRepository
): ViewModel(){
    private val offlineNewsDAO = repository.getOfflineNewsDao()
    private val offlineNews = offlineNewsDAO.getOfflineNews()

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
                viewModelScope.launch {
                    offlineNewsDAO.deleteFromOfflineNews(event.news)
                }
            }
        }
    }

}