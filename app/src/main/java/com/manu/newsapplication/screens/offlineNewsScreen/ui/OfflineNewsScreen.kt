package com.manu.newsapplication.screens.offlineNewsScreen.ui
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            "Offline News",
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif
                        )
                        TextButton(
                            onClick = {onEvent(OfflineNewsScreenEvents.DeleteAllNews)}
                        ){
                            Text(
                                text = "Delete All",
                                color = Color.Red
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )

            )
        }
    ) {innerPadding->
        OfflineNewsScreenContent(
            modifier = Modifier.padding(innerPadding),
            onClickNews = onClickNews,
            state = state.value,
            onEvent = onEvent
        )
    }
}