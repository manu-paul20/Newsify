package com.manu.newsapplication.screens.newsDetailsScreen.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manu.newsapplication.newsReponseModel.Results
import com.manu.newsapplication.screens.newsDetailsScreen.DetailsScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailsScreen(
    results: Results,
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val onEvent = viewModel::onEvent

    DetailsScreenContent(
     results = results,
        state = state.value,
        onEvent = onEvent
    )
}