package com.example.remoteapi.domain.mapper

import com.example.remoteapi.data.entity.BeerEntity
import com.example.remoteapi.domain.model.Beer

fun BeerEntity.toBeer(): Beer {
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}