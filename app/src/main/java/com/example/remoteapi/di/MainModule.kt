package com.example.remoteapi.di

import android.content.Context
import androidx.room.Room
import com.example.remoteapi.data.api.BeerService
import com.example.remoteapi.data.db.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideMainDatabase(
       @ApplicationContext context: Context
    ): MainDatabase {
        return Room.databaseBuilder(
            context,
            MainDatabase::class.java,
            "main_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesBeerService(): BeerService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(BeerService::class.java)
    }

    private const val BASE_URL:String = "https://api.punkapi.com/v2/"
}