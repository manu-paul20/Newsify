package com.manu.newsapplication.screens.homeScreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.manu.newsapplication.retrofit.NetworkResponse
import com.manu.newsapplication.screens.homeScreen.HomeScreenEvents
import com.manu.newsapplication.screens.homeScreen.HomeScreenStates

@Composable
fun HomeScreenContent(
    modifier: Modifier,
    state: HomeScreenStates,
    onEvent: (HomeScreenEvents) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(HomeScreenEvents.GetInitialNews("All"))
    }

    if(state.isShowingFailurePopup){
        FailurePopUp(state,onEvent)
    }

    when(state.initialResponseStatus){
       is NetworkResponse.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }

        }

        NetworkResponse.Success -> {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.newsList.size){index->
                    if(index==state.newsList.size-1){
                        onEvent(HomeScreenEvents.GetNextPage)
                    }
                    val item = state.newsList[index]
                    ListItem(
                        headlineContent = {Text(item.title?:"No Title")},
                        supportingContent = {Text(item.description?:"No Description")}
                    )
                }
                item{
                    when(state.newPageResponseStaus){
                        is NetworkResponse.Loading -> {
                            Row(
                                modifier = modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        is NetworkResponse.Failure -> {
                            Column(
                                modifier = modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("Failed to load next page")
                                IconButton(
                                    onClick = {
                                        onEvent(HomeScreenEvents.GetNextPage)
                                    }
                                ) {
                                    Icon(Icons.Default.RestartAlt,null)
                                }
                            }
                        }
                    }
                }
            }
        }



    }
}
