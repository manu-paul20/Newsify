package com.manu.newsapplication.screens.bookMarksScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.manu.newsapplication.database.entities.BookMarks
import com.manu.newsapplication.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    repository: DatabaseRepository
): ViewModel(){
    private val bookMarksDAO = repository.getBookMarksDao()
    private val bookMarks = bookMarksDAO.getBookMarks()
   private val _state = MutableStateFlow(BookMarkStates())

    val state = combine(bookMarks,_state){bookMarks,state->
        state.copy(
            bookMarks = bookMarks
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BookMarkStates())

    fun onEvent(event: BookMarkEvents){
        when(event){
            is BookMarkEvents.DeleteBookMark -> {
                viewModelScope.launch {
                    bookMarksDAO.deleteFromBookMarks(event.news)
                }
            }
        }
    }

}