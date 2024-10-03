package com.example.foodie.common.data.model

data class FoodResponce(
    val _links: Links,
    val count: Int,
    val from: Int,
    val hits: List<Hit>,
    val to: Int
)