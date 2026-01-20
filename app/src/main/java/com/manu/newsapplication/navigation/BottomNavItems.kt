package com.manu.newsapplication.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.CloudOff
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.ui.graphics.vector.ImageVector
import okhttp3.Route

data class BottomNavItem(
    val icon : ImageVector,
    val title: String
)

val TOP_LEVEL_DESTINATIONS = mapOf(
    Routes.HomeScreen to BottomNavItem(
        icon = Icons.Outlined.Newspaper,
        title = "News"
    ),

    Routes.BookMarksScreen to BottomNavItem(
        icon = Icons.Outlined.Bookmarks,
        title = "Book Marks"
    ),

    Routes.OfflineNewsScreen to BottomNavItem(
        icon = Icons.Outlined.CloudOff,
        title = "Offline News"
    )
)