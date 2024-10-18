package com.example.foodie.dishes.domain.repository

import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.model.FoodResponce

interface FoodRepository  {
    suspend fun searchFoodByCriteria(query: String,mealType: String,time: Float): State<FoodResponce>
}