package com.example.remoteapi.data.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.remoteapi.data.api.BeerService
import com.example.remoteapi.data.db.MainDatabase
import com.example.remoteapi.data.dto.toBeerEntity
import com.example.remoteapi.data.entity.BeerEntity
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator @Inject constructor(
    private val mainDB: MainDatabase,
    private val beerService: BeerService
): RemoteMediator<Int, BeerEntity>(){

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeerEntity>
    ): MediatorResult {
        return try{

            //Calculate loadKey
            val loadKey = when( loadType){
                LoadType.REFRESH -> {
                    //If the list is fully updated
                    1
                }
                LoadType.PREPEND -> {
                    //Prev Page
                    return MediatorResult.Success( endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                   //Next Page
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null){
                        1
                    }else{
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            delay(2000L)
            //Call API
            val beersDTO = beerService.getBeers( page = loadKey, pageCount = state.config.pageSize)

            //Update Db
            mainDB.withTransaction {
                if(loadType == LoadType.REFRESH){
                    mainDB.getBeerDao().deleteAll()
                }
                //Save data into local storage
                val beerEntities = beersDTO.map { it.toBeerEntity() }
                mainDB.getBeerDao().upsertAll(beerEntities)
            }

            MediatorResult.Success( endOfPaginationReached = beersDTO.isEmpty())
        } catch ( e: IOException){
            MediatorResult.Error(e)
        } catch (e: HttpException){
            MediatorResult.Error(e)
        }
    }
}