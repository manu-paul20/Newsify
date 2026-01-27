package com.manu.newsapplication.screens.homeScreen.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manu.newsapplication.screens.homeScreen.HomeScreenEvents
import com.manu.newsapplication.screens.homeScreen.HomeScreenStates

@Composable
fun HomeScreenTopAppBar(
    state: HomeScreenStates,
    onEvent: (HomeScreenEvents) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = "App Name",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Medium
        )

        OutlinedTextField(
            textStyle = TextStyle(
                fontSize = 16.sp
            ),
            colors = TextFieldDefaults.colors(
                focusedTrailingIconColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            ),
            value = state.searchQuery,
            onValueChange = { onEvent(HomeScreenEvents.OnSearchQueryChange(it)) },
            label = { Text(
                text = "Search",
                color = Color.White
            ) },
            singleLine = true,
            trailingIcon = {
                IconButton(
                   onClick = {
                       if(state.searchQuery.isNotBlank()){
                           onEvent(HomeScreenEvents.GetInitialNews(state.searchQuery))
                       }
                   }
                ) {
                    Icon(Icons.Default.Search,null)
                }
            },
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth().height(60.dp),
        )

    }
}