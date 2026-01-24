package com.manu.newsapplication.pagination

import com.manu.newsapplication.newsReponseModel.NewsResponse
import com.manu.newsapplication.retrofit.NetworkResponse

class DefaultPaginator(
    initialKey: String?,
    private val onLoadUpdated:(Boolean)-> Unit,
    private val onRequest: (nextKey: String?) -> NewsResponse,
    private val getNextKey:(newsList: NewsResponse) -> String
){
    private var currentKey = initialKey
    private var isMakingRequest = false
    suspend fun loadNextItems(){
        if(isMakingRequest){
            return
        }

        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        currentKey = getNextKey(result)
        onLoadUpdated(false)
    }

}