package com.manu.newsapplication.screens.newsDetailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manu.newsapplication.database.entities.BookMarks
import com.manu.newsapplication.database.entities.OfflineNews
import com.manu.newsapplication.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    repository: DatabaseRepository
): ViewModel() {
    private val _state = MutableStateFlow(DetailsScreenStates())
    val state = _state.asStateFlow()

    val offlineTodoDao = repository.getOfflineNewsDao()
    val bookMarksDao = repository.getBookMarksDao()


    fun onEvent(event: DetailsScreenEvents) {
        when (event) {

            is DetailsScreenEvents.UpdateInitialSate->{
                _state.update {
                    it.copy(
                        item = event.results
                    )
                }
            }

            is DetailsScreenEvents.SaveNews -> {
                viewModelScope.launch {
                    val news = event.results
                    offlineTodoDao.addToOfflineNews(
                        OfflineNews(
                            title = news.title,
                            description = news.description,
                            source_name = news.source,
                            pubDate = news.pubDate
                        )
                    )
                }

                _state.update {
                    it.copy(
                        isSaved = true
                    )
                }
            }

            is DetailsScreenEvents.BookMarkNews -> {
                val result = event.results
                viewModelScope.launch {
                    bookMarksDao.addToBookMarks(
                        BookMarks(
                            description = result.description,
                            image_url = result.imageUrl,
                            pubDate = result.pubDate,
                            source_name = result.source,
                            source_url = result.sourceUrl,
                            title = result.title
                        )
                    )
                }
                _state.update {
                    it.copy(
                        item = it.item.copy(isBookMarked = true)
                    )
                }
            }

            is DetailsScreenEvents.RemoveBookMark -> {
                val result = event.results
                viewModelScope.launch {
                    bookMarksDao.deleteFromBookMarks(
                        BookMarks(
                            description = result.description,
                            image_url = result.imageUrl,
                            pubDate = result.pubDate,
                            source_name = result.source,
                            source_url = result.sourceUrl,
                            title = result.title
                        )
                    )
                }
                _state.update {
                    it.copy(
                        item = it.item.copy(isBookMarked = false)
                    )
                }
            }


        }
    }
}