package com.manu.newsapplication.screens.homeScreen.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.manu.newsapplication.database.entities.BookMarks
import com.manu.newsapplication.newsReponseModel.Results

@Composable
fun NewsListItem(
    onClickNews:()-> Unit,
    isOfflineMode: Boolean,
    modifier: Modifier,
    onDeleteOfflineNews:(()-> Unit) ?,
    item: BookMarks,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Card(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color(0xFFE0E2EC)),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFFFFF)
            )
        ) {
            Row(
                modifier = Modifier
                    .clickable { onClickNews() }
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {

                if (!isOfflineMode){
                   ListItemImage(item.image_url)
                }
                Spacer(Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(3.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = item.title,
                        maxLines = 2,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = Color(0xFF1A1C1E)
                    )
                    Text(
                        text = item.description,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp,
                        color = Color(0xFF44474E)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.source_name.take(10),
                            fontSize = 11.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color(0xFF005FB7)
                        )
                        Text(" • ")
                        Text(
                            text = item.pubDate?.substring(0, 11) ?: "",
                            fontSize = 11.sp,
                            color = Color(0xFF74777F)
                        )
                    }

                }
            }
        }
       if(isOfflineMode){
           IconButton(
               onClick = {onDeleteOfflineNews?.invoke()}
           ){
               Icon(Icons.Outlined.Delete,null)
           }
       }
    }
}