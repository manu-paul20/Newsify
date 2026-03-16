package com.manu.newsapplication.screens.bookMarksScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.manu.newsapplication.database.entities.BookMarks
import com.manu.newsapplication.repository.DatabaseRepository
import com.manu.newsapplication.screens.newsDetailsScreen.Details
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    repository: DatabaseRepository
): ViewModel(){
    private val bookMarksDAO = repository.getBookMarksDao()
    private val _bookMarks = bookMarksDAO.getBookMarks().map { list->
        list.map {
            Details(
                title = it.title,
                source = it.source_name,
                pubDate = it.pubDate,
                imageUrl = it.image_url,
                description = it.image_url,
                sourceUrl = it.source_url,
                isBookMarked = false
            )
        }
    }
   private val _state = MutableStateFlow(BookMarkStates())

    val state = combine(_bookMarks,_state){bookMarks,state->
        state.copy(
            bookMarks = bookMarks
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BookMarkStates())

}