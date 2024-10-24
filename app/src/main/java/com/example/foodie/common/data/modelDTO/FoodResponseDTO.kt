package com.example.foodie.common.data.modelDTO

data class FoodResponseDTO(
    val count: Int? = null,
    val from: Int? = null,
    val hits: List<HitDTO>? = null,
    val to: Int? = null
)