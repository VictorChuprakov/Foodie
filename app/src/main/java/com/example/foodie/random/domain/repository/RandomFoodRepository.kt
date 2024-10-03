package com.example.foodie.random.domain.repository

import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.model.FoodResponce

interface RandomFoodRepository {
    suspend fun getRandomFood(): State<FoodResponce>
}