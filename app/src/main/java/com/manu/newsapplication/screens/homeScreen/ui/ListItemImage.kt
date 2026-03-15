package com.manu.newsapplication.screens.homeScreen.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun ListItemImage(url: String){
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(5.dp),
    ) {
        AsyncImage(
            modifier = Modifier
                .aspectRatio(1.1f)
                .clip(RoundedCornerShape(12.dp)),
            model = url,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}