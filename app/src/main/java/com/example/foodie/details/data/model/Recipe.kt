package com.example.foodie.details.data.model

data class Recipe(
    val calories: Double,
    val image: String,
    val images: Images,
    val ingredientLines: List<String>,
    val label: String,
    val mealType: List<String>,
    val totalNutrients: TotalNutrients,
    val totalTime: Double,
    val totalWeight: Double,
    val url: String
)