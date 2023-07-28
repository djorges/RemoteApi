package com.example.remoteapi.domain.mapper

import com.example.remoteapi.data.entity.BeerEntity
import com.example.remoteapi.domain.model.Beer

fun BeerEntity.toBeer(): Beer {
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        first_brewed = first_brewed,
        image_url = image_url
    )
}