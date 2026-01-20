package com.manu.newsapplication.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey

@Composable
fun BottomNavigationBar(
    selectedKey: NavKey,
    onSelectKey:(NavKey)-> Unit
){
    BottomAppBar {
        TOP_LEVEL_DESTINATIONS.forEach {(topLevelDestination,values)->
            NavigationBarItem(
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