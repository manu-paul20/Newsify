package com.manu.newsapplication.screens.newsDetailsScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manu.newsapplication.screens.newsDetailsScreen.Details
import com.manu.newsapplication.screens.newsDetailsScreen.DetailsScreenEvents
import com.manu.newsapplication.screens.newsDetailsScreen.DetailsScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailsScreen(
    results: Details,
    onClickBack:()-> Unit,
    isOfflineMode: Boolean,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val onEvent = viewModel::onEvent

    onEvent(DetailsScreenEvents.UpdateInitialSate(results))


   Scaffold(
       topBar = {
           TopAppBar(
               colors = TopAppBarDefaults.topAppBarColors(
                   containerColor = Color(0xFFFFFFFF)
               ),
               title = {
                   Row{
                       IconButton(
                           onClick = { onClickBack() }
                       ) {
                           Icon(
                               imageVector = Icons.AutoMirrored.Default.ArrowBack,
                               contentDescription = null,
                               tint = Color(0xFF1A1C1E)
                           )
                       }
                       Spacer(Modifier.weight(1f))

                       IconButton(
                           onClick = {
                               if(state.value.item.isBookMarked){
                                   onEvent(DetailsScreenEvents.RemoveBookMark(results))
                               }else{
                                   onEvent(DetailsScreenEvents.BookMarkNews(results))
                               }
                           }
                       ) {
                           Icon(
                               imageVector = if(state.value.item.isBookMarked){
                                   Icons.Default.Bookmark
                               }else{
                                   Icons.Outlined.BookmarkAdd
                               },
                               contentDescription = null,
                               tint = Color(0xFF1A1C1E)
                           )
                       }
                       IconButton(
                           onClick = {
                               if (!state.value.isSaved){
                                   onEvent(DetailsScreenEvents.SaveNews(results))
                               }
                           }
                       ) {
                           Icon(
                               imageVector = if(state.value.isSaved){
                                   Icons.Default.Check
                               }else{
                                   Icons.Default.SaveAlt
                               },
                               contentDescription = null,
                               tint = Color(0xFF1A1C1E)

                           )

                       }
                   }
               }
           )
       }
   ) {innerPadding->
       DetailsScreenContent(
           modifier = Modifier.padding(innerPadding),
           isOfflineMode = isOfflineMode,
           results = results
       )
   }
}