package com.manu.newsapplication.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

@Composable
fun NavigationRoot(){
    val navigationState = rememberNavigationState(
        startRoute = Routes.HomeScreen,
        topLevelRoutes = TOP_LEVEL_DESTINATIONS.keys
    )
    val navigator = remember {
        Navigator(navigationState)
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedKey = navigationState.topLevelRoute,
                onSelectKey = {
                    navigator.navigate(it)
                }
            )
        }
    ) {innerPadding->
        NavDisplay(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            onBack = {
                navigator.goBack()
            },
            entries =
                navigationState.toEntries(
                    entryProvider = entryProvider {
                        entry <Routes.HomeScreen>{

                        }
                        entry <Routes.NewsDetailsScreen>{

                        }
                        entry <Routes.BookMarksScreen>{

                        }
                        entry <Routes.OfflineNewsScreen>{

                        }
                    }
                )
        )
    }
}