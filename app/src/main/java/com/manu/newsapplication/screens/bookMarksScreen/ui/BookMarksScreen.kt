package com.manu.newsapplication.screens.bookMarksScreen.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manu.newsapplication.screens.bookMarksScreen.BookMarkViewModel
import com.manu.newsapplication.screens.newsDetailsScreen.Details

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookMarksScreen(
    onClickNews: (Details) -> Unit,
    viewModel: BookMarkViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val onEvent = viewModel::onEvent

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BookMarks") }
            )
        }
    ) {innerPadding->
        BookMarkContent(
            modifier = Modifier.padding(innerPadding),
            onClickNews = onClickNews,
            state = state.value,
            onEvent = onEvent
        )
    }
}