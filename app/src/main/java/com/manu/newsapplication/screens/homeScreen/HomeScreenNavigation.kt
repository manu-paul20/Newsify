package com.manu.newsapplication.screens.homeScreen

import com.manu.newsapplication.newsReponseModel.Results

data class HomeScreenNavigation(
    val newsDetails:(results: Results)-> Unit,
    val offlineNews:()-> Unit
)
