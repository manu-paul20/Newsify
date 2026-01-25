package com.manu.newsapplication.screens.homeScreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
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
    state: HomeScreenStates,
    onEvent: (HomeScreenEvents) -> Unit
) {
    LaunchedEffect(Unit) {
        onEvent(HomeScreenEvents.GetInitialNews("All"))
    }
    // Just for testing purpose. Remove the code tomorrow
    // Write it in a professional way
    when(state.initialResponseStatus){
        NetworkResponse.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        }
        is NetworkResponse.Failure ->{
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Failure in initial")
            }
        }

        NetworkResponse.Success -> {
           LazyColumn(
               modifier = Modifier.fillMaxSize(),
               verticalArrangement = Arrangement.spacedBy(16.dp)
           ) {
               items(state.newsList.size){index->
                   if(index>=state.newsList.size - 1){
                       onEvent(HomeScreenEvents.GetNextPage)
                   }
                   ListItem(
                       headlineContent = {Text(state.newsList[index].title)},
                       supportingContent = {Text(state.newsList[index].description)}
                   )
               }
               item {
                   when(state.newPageResponseStaus){
                       NetworkResponse.Loading -> {
                           CircularProgressIndicator()
                       }
                       is NetworkResponse.Failure -> {
                           Text("Failure in new page")
                       }
                   }
               }
           }
        }

    }
}
