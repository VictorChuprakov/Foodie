package com.example.foodie.common.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodie.common.data.model.Images
import com.example.foodie.common.data.model.TotalNutrients

@Entity(tableName = "favorite_recipe")
data class FavoriteRecipe(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val calories: Double,
    val image: String,
    val images: Images,
    val label: String,
    val mealType: List<String>,
    val totalTime: Double,
    val ingredientLines: List<String>,
    val totalNutrients: TotalNutrients,
    val totalWeight: Double,
    val url: String,
    val uri: String
)
