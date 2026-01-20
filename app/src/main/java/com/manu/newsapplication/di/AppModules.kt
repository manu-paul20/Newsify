package com.manu.newsapplication.di

import com.manu.newsapplication.constants.MyConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.manu.newsapplication.retrofit.ApiRequests
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    @Singleton
    fun provideApiRequests(): ApiRequests{
        return Retrofit.Builder()
            .baseUrl(MyConstants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)
    }

}