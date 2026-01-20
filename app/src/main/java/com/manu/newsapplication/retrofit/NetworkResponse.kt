package com.manu.newsapplication.retrofit

import com.manu.newsapplication.newsReponseModel.newsResponse

interface NetworkResponse {
    data class Success(val data: newsResponse): NetworkResponse
    data class Failure(val errorMessage: String): NetworkResponse
    object Loading: NetworkResponse
}