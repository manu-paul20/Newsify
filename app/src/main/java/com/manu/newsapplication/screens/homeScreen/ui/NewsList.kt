package com.manu.newsapplication.screens.homeScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.manu.newsapplication.retrofit.NetworkResponse
import com.manu.newsapplication.screens.homeScreen.HomeScreenEvents
import com.manu.newsapplication.screens.homeScreen.HomeScreenStates

@Composable
fun NewsList(
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
        items(state.newsList.size) { index ->
            if (index == state.newsList.size - 1 &&
                state.newPageResponseStaus is NetworkResponse.Success
            ) {
                onEvent(HomeScreenEvents.GetNextPage)
            }
            val item = state.newsList[index]
            NewsListItem(item)
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