package com.example.foodie.common.data.api

import com.example.foodie.common.data.model.FoodResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("recipes/v2")
    suspend fun randomFood(
        @Query("type") type: String,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Query("q") query: String,
        @Query("mealType") mealType: List<String>,
        @Query("time") time: String,
        @Query("imageSize") imageSize: String,
        @Query("field") fields: List<String>,
        @Query("random") random: Boolean,
    ): Response<FoodResponseDTO>


    @GET("recipes/v2/by-uri")
    suspend fun getFoodId(
        @Query("type") type: String,
        @Query("uri") uri: String,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Query("field") field: List<String>
    ): Response<FoodResponseDTO>
}