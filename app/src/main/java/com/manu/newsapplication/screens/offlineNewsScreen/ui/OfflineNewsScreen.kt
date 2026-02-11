package com.manu.newsapplication.screens.offlineNewsScreen.ui

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manu.newsapplication.screens.bookMarksScreen.BookMarkViewModel
import com.manu.newsapplication.screens.bookMarksScreen.ui.BookMarkContent
import com.manu.newsapplication.screens.newsDetailsScreen.Details
import com.manu.newsapplication.screens.offlineNewsScreen.OfflineNewsScreenEvents
import com.manu.newsapplication.screens.offlineNewsScreen.OfflineScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfflineNewsScreen(
    onClickNews: (Details) -> Unit,
    viewModel: OfflineScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val onEvent = viewModel::onEvent

    onEvent(OfflineNewsScreenEvents.updateInitialState)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    "Offline News",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                ) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )

            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {onEvent(OfflineNewsScreenEvents.DeleteSelected)},
                content = {Text("Delete")}
            )
        }
    ) {innerPadding->
        OfflineNewsScreenContennt(
            modifier = Modifier.padding(innerPadding),
            onClickNews = onClickNews,
            state = state.value,
            onEvent = onEvent
        )
    }
}