package com.manu.newsapplication.screens.newsDetailsScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Outlined.Bookmark,
                contentDescription = null,
            )
        }
        SubcomposeAsyncImage(
            model = results.image_url,
            contentDescription = null,
            modifier = Modifier.aspectRatio(1.1f)
        )
    }
}