package com.example.foodie.dishes.data.model

data class FoodRandom(
    val _links: Links,
    val count: Int,
    val from: Int,
    val hits: List<Hit>,
    val to: Int
)