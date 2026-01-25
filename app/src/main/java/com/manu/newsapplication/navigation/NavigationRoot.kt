package com.manu.newsapplication.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.manu.newsapplication.screens.homeScreen.HomeScreen
import okhttp3.Route

@OptIn(ExperimentalMaterial3Api::class)
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
        },
    ) {_->
        NavDisplay(
            modifier = Modifier.fillMaxSize(),
            onBack = {
                navigator.goBack()
            },
            entries =
                navigationState.toEntries(
                    entryProvider = entryProvider {
                        entry <Routes.HomeScreen>{
                            HomeScreen()
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