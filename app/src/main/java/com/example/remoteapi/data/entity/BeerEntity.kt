package com.example.remoteapi.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("beers")
data class BeerEntity (
    @PrimaryKey
    val id: Int,
    val name: String,
    val tagline: String,
    val description:String,
    val first_brewed:String,
    val image_url:String
)