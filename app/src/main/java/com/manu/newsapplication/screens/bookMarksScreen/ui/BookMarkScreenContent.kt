package com.manu.newsapplication.screens.bookMarksScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.manu.newsapplication.screens.bookMarksScreen.BookMarkEvents
import com.manu.newsapplication.screens.bookMarksScreen.BookMarkStates
import com.manu.newsapplication.screens.homeScreen.ui.NewsListItem
import com.manu.newsapplication.screens.newsDetailsScreen.Details

@Composable
fun BookMarkContent(
    modifier: Modifier,
    onClickNews: (Details) -> Unit,
    state: BookMarkStates,
    onEvent: (BookMarkEvents) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFFDFBFF))
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        verticalArrangement = if(state.bookMarks.isEmpty()){
            Arrangement.Center
        }else{
            Arrangement.spacedBy(16.dp)
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(state.bookMarks.isEmpty()){
            item {
                Text("Nothing Here")
            }
        }else{
            items(state.bookMarks) {
                NewsListItem(
                    onClickNews = {
                        onClickNews(
                            Details(
                                title = it.title,
                                description = it.description,
                                imageUrl = it.image_url,
                                pubDate = it.pubDate,
                                source = it.source_name,
                                sourceUrl = it.source_url,
                                isBookMarked = true
                            )
                        )
                    },
                    item = it
                )
            }
        }
    }
}