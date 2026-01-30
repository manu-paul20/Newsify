package com.manu.newsapplication.screens.newsDetailsScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.SubcomposeAsyncImage
import com.manu.newsapplication.newsReponseModel.Results
import com.manu.newsapplication.screens.newsDetailsScreen.DetailsScreenEvents
import com.manu.newsapplication.screens.newsDetailsScreen.DetailsScreenStates

@Composable
fun DetailsScreenContent(
    results: Results,
    state: DetailsScreenStates,
    onEvent:(DetailsScreenEvents)-> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .background(Color.Transparent)

        ) {
            SubcomposeAsyncImage(
                model = results.image_url,
                contentDescription = null,
               modifier = Modifier.aspectRatio(1.1f)
            )
        }
    }
}