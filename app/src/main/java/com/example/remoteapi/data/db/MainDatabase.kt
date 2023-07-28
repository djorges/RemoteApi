package com.example.remoteapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.remoteapi.data.dao.BeerDao
import com.example.remoteapi.data.entity.BeerEntity

@Database(
    entities = [ BeerEntity::class],
    version = 1
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun getBeerDao() : BeerDao
}