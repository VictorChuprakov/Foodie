package com.example.foodie.dishes.data.modelDTO

data class RecipeDTO(
    val image: String? = null,
    val images: ImagesDTO? = null,
    val label: String? = null,
    val mealType: List<String>? = null,
    val totalTime: Double? = null,
    val uri: String? = null
)