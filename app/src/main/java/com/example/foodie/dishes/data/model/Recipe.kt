package com.example.foodie.dishes.data.model

data class Recipe(
    val image: String,
    val images: Images,
    val label: String,
    val mealType: List<String>,
    val totalTime: Double,
    val uri: String
)