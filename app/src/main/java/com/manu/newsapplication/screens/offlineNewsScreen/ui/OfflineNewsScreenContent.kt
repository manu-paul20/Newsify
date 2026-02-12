package com.manu.newsapplication.screens.offlineNewsScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.manu.newsapplication.database.entities.BookMarks
import com.manu.newsapplication.screens.bookMarksScreen.BookMarkStates
import com.manu.newsapplication.screens.homeScreen.ui.NewsListItem
import com.manu.newsapplication.screens.newsDetailsScreen.Details
import com.manu.newsapplication.screens.offlineNewsScreen.OfflineNewsScreenEvents
import com.manu.newsapplication.screens.offlineNewsScreen.OfflineNewsScreenState

@Composable
fun OfflineNewsScreenContennt(
    modifier: Modifier,
    onClickNews: (Details) -> Unit,
    state: OfflineNewsScreenState,
    onEvent:(OfflineNewsScreenEvents)-> Unit
) {
    if(state.isLoading){
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }
    LazyColumn(
        modifier = modifier
            .background(color = Color(0xFFFDFBFF))
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        verticalArrangement = if (state.offlineNews.isEmpty()) {
            Arrangement.Center
        } else {
            Arrangement.spacedBy(16.dp)
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.offlineNews.isEmpty()) {
            item {
                Text("Nothing Here")
            }
        } else {
            items(state.offlineNews) {
                NewsListItem(
                    onClickNews = {
                        onClickNews(
                            Details(
                                title = it.title,
                                description = it.description,
                                imageUrl = "",
                                pubDate = it.pubDate,
                                source = it.source_name,
                                sourceUrl = "",
                                isBookMarked = true
                            )
                        )
                    },
                    isSelected = it.isSelected,
                    isOfflineMode = true,
                    onSelect = {onEvent(OfflineNewsScreenEvents.SelectNews(it))},
                    item = BookMarks(
                        title = it.title,
                        description = it.description,
                        pubDate = it.pubDate,
                        source_name = it.source_name,
                        image_url = "",
                        source_url = ""
                    )
                )
            }
        }
    }
}