package com.manu.newsapplication.screens.homeScreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manu.newsapplication.screens.homeScreen.HomeScreenEvents
import com.manu.newsapplication.screens.homeScreen.HomeScreenStates

@Composable
fun FailurePopUp(
  state: HomeScreenStates,
    onEvent:(HomeScreenEvents)-> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onEvent(HomeScreenEvents.HideFailurePopUp)
        },
        icon = {
            Icon(Icons.Default.SentimentDissatisfied,null)
        },
        title = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Oops! Something went wrong 🤔")
                Text(state.errorMessage, fontSize = 18.sp)
            }
        },
        confirmButton = {
            FilledTonalButton(
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    onEvent(HomeScreenEvents.HideFailurePopUp)
                    onEvent(HomeScreenEvents.GetInitialNews(state.searchQuery))
                }
            ) {
                Text("Retry")
            }
        },
        dismissButton = {
            FilledTonalButton(
                shape = RoundedCornerShape(20.dp),
                onClick = {

                }
            ) {
                Text("Offline News")
            }
        }
    )
}