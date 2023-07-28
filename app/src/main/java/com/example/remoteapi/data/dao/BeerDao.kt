package com.example.remoteapi.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.remoteapi.data.entity.BeerEntity

@Dao
interface BeerDao {
    @Upsert
    suspend fun upsertAll(beers: List<BeerEntity>)

    @Query("SELECT * FROM beers")
    fun getAll(): PagingSource<Int, BeerEntity>

    @Query("DELETE FROM beers")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(beer:BeerEntity)
}