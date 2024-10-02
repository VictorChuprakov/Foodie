package com.example.foodie.details.data.modelDTO

data class FoodDetailsDTO(
    val _links: LinksDTO? = null,
    val count: Int? = null,
    val from: Int? = null,
    val hits: List<HitDTO>? = null,
    val to: Int? = null
)