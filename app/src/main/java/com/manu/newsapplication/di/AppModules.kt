package com.manu.newsapplication.di

import android.content.Context
import androidx.room.Room
import com.manu.newsapplication.constants.MyConstants
import com.manu.newsapplication.database.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.manu.newsapplication.retrofit.ApiRequests
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideNewsDataBase(
        @ApplicationContext context: Context
    ): NewsDataBase{
      return  Room.databaseBuilder(
            context = context,
            klass = NewsDataBase::class.java,
            name = "News.DB"
        ).build()
    }
}