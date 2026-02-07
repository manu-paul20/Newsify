package com.manu.newsapplication.screens.newsDetailsScreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.manu.newsapplication.screens.newsDetailsScreen.Details
import com.manu.newsapplication.screens.newsDetailsScreen.DetailsScreenEvents
import com.manu.newsapplication.screens.newsDetailsScreen.DetailsScreenStates

@Composable
fun DetailsScreenContent(
    results: Details,
    state: DetailsScreenStates,
    onEvent:(DetailsScreenEvents)-> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ){
                SubcomposeAsyncImage(
                    model = results.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentScale = ContentScale.Crop,
                    error = { Icon(Icons.Default.BrokenImage, null) },
                )
        }
        // title
        Text(
            text = results.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        HorizontalDivider()

        //Source name and publish date
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = results.source,
                color = Color(0xFF005FB7),
                fontSize = 14.sp
            )
            Text(" • ")
            Text(
                text = results.pubDate.take(11),
                fontSize = 14.sp,
                color = Color(0xFF74777F)
            )
        }
    }
}