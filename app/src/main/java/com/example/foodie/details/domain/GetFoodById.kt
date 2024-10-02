package com.example.foodie.details.domain

import com.example.foodie.common.data.api.State
import com.example.foodie.details.data.model.FoodDetails

interface GetFoodById {
    suspend fun GetFoodId (uri: String): State<FoodDetails>
}