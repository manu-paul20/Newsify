package com.manu.newsapplication.navigation

import androidx.navigation3.runtime.NavKey

class Navigator(
    val navigationState: NavigationState
) {
    fun navigate(route: NavKey){
        if (route in navigationState.backStack.keys){
            navigationState.topLevelRoute = route
        }else{
            navigationState.backStack[navigationState.topLevelRoute]?.add(route)
        }
    }

    fun goBack(){
        val currentBackStack = navigationState.backStack[navigationState.topLevelRoute]
        val currentRoute = currentBackStack?.lastOrNull()
        if(currentRoute == navigationState.topLevelRoute){
            navigationState.topLevelRoute = navigationState.startRoute
        }else{
            currentBackStack?.removeLastOrNull()
        }
    }
}
