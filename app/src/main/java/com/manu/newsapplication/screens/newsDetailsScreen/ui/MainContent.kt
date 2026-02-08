package com.manu.newsapplication.screens.newsDetailsScreen.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manu.newsapplication.screens.newsDetailsScreen.Details

@Composable
fun MainContent(
    news: Details
) {
    //title
    Text(
        text = news.title,
        color = Color(0xFF201A19),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(5.dp)
    )
    Spacer(Modifier.height(5.dp))

    //source name and pub date
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        //source name
        Text(
            text = news.source,
            color = Color(0XFF9A4522),
            fontSize = 14.sp
        )
        Text("  •  ")

        //date
        Text(
            text = news.pubDate.take(11),
            fontSize = 14.sp,
            color = Color(0xFF74777F)
        )
    }
    Spacer(Modifier.height(10.dp))
    HorizontalDivider()
    Spacer(Modifier.height(10.dp))

    //description
    Text(
        text = news.description,
        color = Color(0xFF534341),
        fontSize = 18.sp,
        modifier = Modifier.padding(5.dp)
    )
}