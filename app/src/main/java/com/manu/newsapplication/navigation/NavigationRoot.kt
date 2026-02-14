package com.manu.newsapplication.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.manu.newsapplication.screens.bookMarksScreen.ui.BookMarksScreen
import com.manu.newsapplication.screens.homeScreen.HomeScreen
import com.manu.newsapplication.screens.homeScreen.HomeScreenNavigation
import com.manu.newsapplication.screens.newsDetailsScreen.ui.NewsDetailsScreen
import com.manu.newsapplication.screens.offlineNewsScreen.ui.OfflineNewsScreen

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
        floatingActionButton = {},
        bottomBar = {
            if(currentRoute is Routes.NewsDetailsScreen){
                if(!currentRoute.isOfflineMode){
                    BottomAppBar(
                        tonalElevation = BottomAppBarDefaults.ContainerElevation,
                        content = {
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
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
                }
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
            transitionSpec = {
                slideInHorizontally { it }+ fadeIn() togetherWith
                        slideOutHorizontally { -it } + fadeOut()
            },
            popTransitionSpec = {
                slideInHorizontally{-it}.togetherWith(slideOutHorizontally { it })
            },
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
                                    navigator.navigate(
                                        Routes.NewsDetailsScreen(
                                            results = it,
                                            isOfflineMode = false
                                        )
                                    )
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
                                isOfflineMode = it.isOfflineMode,
                                onClickBack = {
                                    navigator.goBack()
                                }
                            )
                        }
                        entry <Routes.BookMarksScreen>{
                            BookMarksScreen(
                                onClickNews = {
                                    navigator.navigate(
                                        Routes.NewsDetailsScreen(
                                            results = it,
                                            isOfflineMode = false
                                        )
                                    )
                                }
                            )
                        }
                        entry <Routes.OfflineNewsScreen>{
                            OfflineNewsScreen(
                                onClickNews = {
                                    navigator.navigate(
                                        Routes.NewsDetailsScreen(
                                            results = it,
                                            isOfflineMode = true
                                        )
                                    )
                                }
                            )
                        }
                    }
                )
        )
    }
}