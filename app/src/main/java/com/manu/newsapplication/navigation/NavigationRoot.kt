package com.manu.newsapplication.navigation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.manu.newsapplication.screens.bookMarksScreen.ui.BookMarksScreen
import com.manu.newsapplication.screens.homeScreen.HomeScreen
import com.manu.newsapplication.screens.homeScreen.HomeScreenNavigation
import com.manu.newsapplication.screens.newsDetailsScreen.ui.NewsDetailsScreen
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
    val localUriHandler = LocalUriHandler.current
    val currentRoute = navigationState.backStack[navigationState.topLevelRoute]?.lastOrNull()
    Scaffold(
        bottomBar = {
            if(currentRoute is Routes.NewsDetailsScreen){
                BottomAppBar(
                    tonalElevation = BottomAppBarDefaults.ContainerElevation,
                    content = {
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF201A19)
                            ),
                            onClick = {
                             localUriHandler.openUri(currentRoute.results.sourceUrl)
                            }
                        ) {
                            Text("Read full news")
                        }
                    }
                )
            }else{
                BottomNavigationBar(
                    selectedKey = navigationState.topLevelRoute,
                    onSelectKey = {
                        navigator.navigate(it)
                    }
                )
            }
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
                            val nav = HomeScreenNavigation(
                                newsDetails = {
                                    navigator.navigate(Routes.NewsDetailsScreen(it))
                                },
                                offlineNews = {
                                   navigator.navigate(Routes.OfflineNewsScreen)
                                }
                            )
                            HomeScreen(
                                navigation = nav
                            )
                        }
                        entry <Routes.NewsDetailsScreen>{
                            NewsDetailsScreen(
                                results = it.results,
                                onClickBack = {
                                    navigator.goBack()
                                }
                            )
                        }
                        entry <Routes.BookMarksScreen>{
                            BookMarksScreen(
                                onClickNews = {navigator.navigate(Routes.NewsDetailsScreen(it))}
                            )
                        }
                        entry <Routes.OfflineNewsScreen>{

                        }
                    }
                )
        )
    }
}