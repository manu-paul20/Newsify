package com.manu.newsapplication.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.NavKey

@Composable
fun BottomNavigationBar(
    selectedKey: NavKey,
    onSelectKey:(NavKey)-> Unit
){
    BottomAppBar(
        containerColor = Color(0xFFF1F0F4)
    ) {
        TOP_LEVEL_DESTINATIONS.forEach {(topLevelDestination,values)->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF005FB7),
                    selectedTextColor = Color(0xFF005FB7),
                    unselectedIconColor = Color(0xFF44474E),
                    unselectedTextColor = Color(0xFF44474E),
                    indicatorColor = Color(0xFFD1E4FF)
                ),
                selected = (selectedKey == topLevelDestination),
                onClick = {onSelectKey(topLevelDestination)},
                icon = { Icon(
                    imageVector = values.icon,
                    contentDescription = values.title
                ) },
                label = { Text(values.title) }
            )
        }
    }
}