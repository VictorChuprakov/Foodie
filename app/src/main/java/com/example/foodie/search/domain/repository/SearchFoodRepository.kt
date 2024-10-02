package com.example.foodie.search.domain.repository

import com.example.foodie.common.data.api.State
import com.example.foodie.dishes.data.model.FoodRandom

interface SearchFoodRepository {
    suspend fun getSearchFood(): State<FoodRandom>
}