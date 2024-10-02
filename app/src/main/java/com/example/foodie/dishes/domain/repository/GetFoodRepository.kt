package com.example.foodie.dishes.domain.repository

import com.example.foodie.common.data.api.State
import com.example.foodie.dishes.data.model.FoodRandom
import com.example.foodie.dishes.data.modelDTO.FoodRandomDTO

interface GetFoodRepository {
    suspend fun getFood(): State<FoodRandom>
}