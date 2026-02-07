package com.manu.newsapplication.screens.newsDetailsScreen.ui

import android.telecom.Call
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manu.newsapplication.newsReponseModel.Results
import com.manu.newsapplication.screens.newsDetailsScreen.Details
import com.manu.newsapplication.screens.newsDetailsScreen.DetailsScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailsScreen(
    results: Details,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val onEvent = viewModel::onEvent

   Scaffold(
       topBar = {
           TopAppBar(
               title = {
                   Row{
                       IconButton(
                           onClick = {}
                       ) {
                           Icon(Icons.AutoMirrored.Default.ArrowBack,null)
                       }
                       Spacer(Modifier.weight(1f))

                       IconButton(
                           onClick = {}
                       ) {
                           Icon(Icons.Outlined.Bookmark,null)
                       }
                       IconButton(
                           onClick = {}
                       ) {
                           Icon(Icons.Default.SaveAlt,null)
                       }
                   }
               }
           )
       }
   ) {innerPadding->
       DetailsScreenContent(
           modifier = Modifier.padding(innerPadding),
           results = results,
           state = state.value,
           onEvent = onEvent
       )
   }
}