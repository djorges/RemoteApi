package com.example.remoteapi.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.remoteapi.data.api.BeerService
import com.example.remoteapi.data.datasource.BeerRemoteMediator
import com.example.remoteapi.data.db.MainDatabase
import com.example.remoteapi.data.entity.BeerEntity
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

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBeerPager(
        mainDB: MainDatabase,
        beerService: BeerService
    ): Pager<Int, BeerEntity> {
        return Pager(
            config = PagingConfig( pageSize = ITEMS_PER_PAGE),
            remoteMediator = BeerRemoteMediator(
                mainDB = mainDB,
                beerService = beerService
            ),
            pagingSourceFactory = {
                mainDB.getBeerDao().getAll()
            }
        )
    }

    private const val ITEMS_PER_PAGE:Int = 20
    private const val BASE_URL:String = "https://api.punkapi.com/v2/"
}