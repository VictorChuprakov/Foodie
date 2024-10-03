package com.example.foodie.common.data.model

data class FoodResponseDTO(
    val _links: LinksDTO? = null,
    val count: Int? = null,
    val from: Int? = null,
    val hits: List<HitDTO>? = null,
    val to: Int? = null
)