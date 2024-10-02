package com.example.foodie.details.data.model

data class FoodDetails(
    val _links: Links,
    val count: Int,
    val from: Int,
    val hits: List<Hit>,
    val to: Int
)