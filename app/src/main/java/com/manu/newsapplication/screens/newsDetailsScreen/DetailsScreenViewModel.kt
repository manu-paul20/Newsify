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
                val news = event.results
                viewModelScope.launch {
                    bookMarksDao.addToBookMarks(
                        BookMarks(
                            title = news.title,
                            description = news.description,
                            image_url = news.imageUrl,
                            link = news.sourceUrl,
                            pubDate = news.pubDate,
                            source_name = news.source,
                            source_url = news.sourceUrl,
                        )
                    )
                }

                _state.update {
                    it.copy(
                        isBookMarked = true
                    )
                }
            }

            is DetailsScreenEvents.RemoveBookMark -> {
                val news = event.results
                viewModelScope.launch {
                    bookMarksDao.deleteFromBookMarks(
                        BookMarks(
                            title = news.title,
                            description = news.description,
                            image_url = news.imageUrl,
                            link = news.sourceUrl,
                            pubDate = news.pubDate,
                            source_name = news.source,
                            source_url = news.sourceUrl,
                        )
                    )
                }
            }
        }
    }
}