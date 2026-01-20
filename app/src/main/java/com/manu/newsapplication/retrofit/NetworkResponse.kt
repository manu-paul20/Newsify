package com.manu.newsapplication.retrofit

import com.manu.newsapplication.retrofit.newsReponseModel.News_response

interface NetworkResponse {
    data class Success(val data: News_response): NetworkResponse
    data class Failure(val errorMessage: String): NetworkResponse
    object Loading: NetworkResponse
}