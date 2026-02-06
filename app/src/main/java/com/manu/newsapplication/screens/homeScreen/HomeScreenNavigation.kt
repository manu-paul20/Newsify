package com.manu.newsapplication.screens.homeScreen

import com.manu.newsapplication.newsReponseModel.Results
import com.manu.newsapplication.screens.newsDetailsScreen.Details

data class HomeScreenNavigation(
    val newsDetails:(results: Details)-> Unit,
    val offlineNews:()-> Unit
)
