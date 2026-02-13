package com.manu.newsapplication.screens.homeScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.manu.newsapplication.database.entities.BookMarks
import com.manu.newsapplication.retrofit.NetworkResponse
import com.manu.newsapplication.screens.homeScreen.HomeScreenEvents
import com.manu.newsapplication.screens.homeScreen.HomeScreenNavigation
import com.manu.newsapplication.screens.homeScreen.HomeScreenStates
import com.manu.newsapplication.screens.homeScreen.SuggestionChips
import com.manu.newsapplication.screens.newsDetailsScreen.Details

@Composable
fun NewsList(
    navigation: HomeScreenNavigation,
    modifier: Modifier,
    state: HomeScreenStates,
    onEvent: (HomeScreenEvents) -> Unit
) {
    LazyColumn(

        modifier = modifier
            .background(color = Color(0xFFFDFBFF))
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                SuggestionChips.entries.forEach {
                    FilterChip(
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color(0x1AFFFFFF),
                            selectedContainerColor = Color(0xCC005FB7),
                            selectedLabelColor = Color(0xFFE2E2E6)
                        ),
                        selected =(state.currentChip == it),
                        onClick = {
                            onEvent(HomeScreenEvents.ChangeSuggestionChip(it))
                        },
                        label = {Text(it.name)}
                    )
                }
            }
        }
        items(state.resultsList.size) { index ->
            if (index == state.resultsList.size - 1 &&
                state.newPageResponseStaus is NetworkResponse.Success
            ) {
                onEvent(HomeScreenEvents.GetNextPage)
            }
            val item = state.resultsList[index]
            NewsListItem(
                onClickNews = { navigation.newsDetails(
                    Details(
                        imageUrl = item.image_url?:"",
                        title = item.title?:"",
                        description = item.description?:"",
                        pubDate = item.pubDate?:"",
                        source = item.source_name?:"",
                        sourceUrl = item.link?:"",
                        isBookMarked = false
                    )
                ) },
                isOfflineMode = false,
                modifier = Modifier,
               onDeleteOfflineNews = null,
                item = BookMarks(
                    description = item.description?:"",
                    image_url = item.image_url?:"",
                    pubDate = item.pubDate?:"",
                    source_name = item.source_name?:"",
                    source_url = item.source_url?:"",
                    title = item.title?:""
                )
            )


        }
        item {
            when (state.newPageResponseStaus) {

                is NetworkResponse.Loading -> {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 100.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is NetworkResponse.Failure -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 100.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Failed to load next page")
                        IconButton(
                            onClick = {
                                onEvent(HomeScreenEvents.GetNextPage)
                            }
                        ) {
                            Icon(Icons.Default.RestartAlt, null)
                        }
                    }
                }
            }
        }
    }

}