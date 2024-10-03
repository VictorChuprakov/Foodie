package com.example.foodie.search.domain.repository

import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.model.FoodResponce

interface SearchFoodRepository {
    suspend fun searchFood(query: String): State<FoodResponce>
}