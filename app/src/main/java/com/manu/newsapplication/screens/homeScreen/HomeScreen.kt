package com.manu.newsapplication.screens.homeScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.manu.newsapplication.screens.homeScreen.ui.HomeScreenContent
import com.manu.newsapplication.screens.homeScreen.ui.HomeScreenTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigation: HomeScreenNavigation,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val onEvent = viewModel::onEvent
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1A1C1E),
                    titleContentColor = Color(0xFFE2E2E6)
                ),
                title = {
                    HomeScreenTopAppBar(state,onEvent)
                },
            )
        }
    ) {innerPadding->
        HomeScreenContent(
            modifier = Modifier.padding(innerPadding),
            navigation = navigation,
            state = state,
            onEvent = onEvent
        )
    }
}