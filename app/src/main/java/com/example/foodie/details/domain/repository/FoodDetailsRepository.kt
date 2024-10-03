package com.example.foodie.details.domain.repository

import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.model.FoodResponce

interface FoodDetailsRepository {
    suspend fun getFood (id: String): State<FoodResponce>
}