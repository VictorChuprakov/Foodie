package com.example.foodie.profile.ui.components

import com.example.foodie.common.data.model.Recipe
import com.example.foodie.common.data.room.entities.FavoriteRecipe

fun convertRecipeToFavoriteRecipe(recipe: Recipe): FavoriteRecipe {
    return FavoriteRecipe(
        calories = recipe.calories,
        image = recipe.image,
        images = recipe.images,
        label = recipe.label,
        mealType = recipe.mealType,
        totalTime = recipe.totalTime,
        ingredientLines = recipe.ingredientLines,
        totalNutrients = recipe.totalNutrients,
        totalWeight = recipe.totalWeight,
        url = recipe.url,
        uri = recipe.uri
    )
}