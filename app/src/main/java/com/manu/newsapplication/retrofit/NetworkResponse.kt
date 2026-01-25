package com.manu.newsapplication.retrofit


interface NetworkResponse {
    object Success: NetworkResponse
    data class Failure(val errorMessage: String): NetworkResponse
    object Loading: NetworkResponse

    object Idle: NetworkResponse
}