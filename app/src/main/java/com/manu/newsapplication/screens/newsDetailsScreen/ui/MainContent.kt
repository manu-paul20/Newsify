package com.manu.newsapplication.screens.newsDetailsScreen.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.manu.newsapplication.screens.newsDetailsScreen.Details

@Composable
fun MainContent(
    news: Details
) {
    Text(
        text = news.title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
    HorizontalDivider()

    //Source name and publish date
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = news.source,
            color = Color(0xFF005FB7),
            fontSize = 14.sp
        )
        Text(" • ")
        Text(
            text = news.pubDate.take(11),
            fontSize = 14.sp,
            color = Color(0xFF74777F)
        )
    }
}