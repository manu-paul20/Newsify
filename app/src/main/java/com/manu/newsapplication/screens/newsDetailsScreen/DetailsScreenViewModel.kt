package com.manu.newsapplication.screens.newsDetailsScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manu.newsapplication.database.NewsDataBase
import com.manu.newsapplication.domain.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
   private val db: NewsDataBase
): ViewModel(){
    private val _state = MutableStateFlow(DetailsScreenStates())
    val state = _state.asStateFlow()

    val offlineTodoDao = db.offlineNewsDao
    val bookMarksDao = db.bookMarksDao


    fun onEvent(event: DetailsScreenEvents){
        when(event){
            is DetailsScreenEvents.SaveNews -> {
                viewModelScope.launch {
                    offlineTodoDao.
                }
            }

            is DetailsScreenEvents.BookMarkNews -> {

            }
        }
    }
}