package com.manu.newsapplication.screens.homeScreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
           NewsList(
               modifier = modifier,
               state = state,
               onEvent = onEvent
           )
        }



    }
}
