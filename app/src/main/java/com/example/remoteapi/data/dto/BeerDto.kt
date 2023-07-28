package com.example.remoteapi.data.dto

import com.example.remoteapi.data.entity.BeerEntity

data class BeerDto (
    val id: Int,
    val name: String,
    val tagline: String,
    val description:String,
    val first_brewed:String,
    val image_url:String
)

fun BeerDto.toBeerEntity(): BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        first_brewed = first_brewed,
        image_url = image_url
    )
}