package com.example.foodie.details.data.modelDTO

data class RecipeDTO(
    val calories: Double? = null,
    val image: String? = null,
    val images: ImagesDTO? = null,
    val ingredientLines: List<String>? = null,
    val label: String? = null,
    val mealType: List<String>? = null,
    val totalNutrients: TotalNutrientsDTO? = null,
    val totalTime: Double? = null,
    val totalWeight: Double? = null,
    val url: String? = null
)