package com.manu.newsapplication.screens.bookMarksScreen.ui

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manu.newsapplication.screens.bookMarksScreen.BookMarkViewModel

@Composable
fun BookMarksScreen(
    viewModel: BookMarkViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val onEvent = viewModel::onEvent

}