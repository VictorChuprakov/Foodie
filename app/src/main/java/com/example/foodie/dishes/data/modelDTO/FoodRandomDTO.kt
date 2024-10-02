package com.example.foodie.dishes.data.modelDTO

data class FoodRandomDTO(
    val _links: LinksDTO? = null,
    val count: Int? = null,
    val from: Int? = null,
    val hits: List<HitDTO>? = null,
    val to: Int? = null
)