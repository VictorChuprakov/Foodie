package com.example.foodie.dishes.data.repository

import com.example.foodie.common.data.api.ApiError
import com.example.foodie.common.data.api.ApiInterface
import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.constans.RetrofitConstants
import com.example.foodie.common.data.mapper.toFoodResponce
import com.example.foodie.common.data.model.FoodResponce
import com.example.foodie.dishes.domain.repository.FoodRepository
import java.io.IOException

class FoodRepositoryImpl(private val apiInterface: ApiInterface) : FoodRepository  {
    override suspend fun searchFoodByCriteria(query: String,mealType: String,time: Float): State<FoodResponce> {
        return try {
            val mealTypes = getMealTypes(mealType)
            val responce = apiInterface.randomFood(
                "public",
                RetrofitConstants.API_ID,
                RetrofitConstants.API_KEY,
                query,
                mealTypes,
                time.toInt().toString()+"+",
                "LARGE",
                listOf("label",
                    "image",
                    "images",
                    "totalNutrients",
                    "ingredientLines",
                    "totalTime",
                    "calories",
                    "mealType",
                    "totalWeight",
                    "url",
                    "uri"),
                true,
            )

            // Проверяем успешность запроса
            if (responce.isSuccessful) {
                val rawBody = responce.body()
                val body = rawBody?.toFoodResponce()
                if (body != null) {
                    State.Success(body)
                } else {
                    State.Error(ApiError.RESPONSE_NULL)
                }
            } else {
                State.Error(ApiError.REQUEST_FAILED)
            }
        } catch (e: IOException) {
            State.Error(ApiError.NETWORK_ERROR)
        } catch (e: Exception) {
            State.Error(ApiError.UNEXPECTED_ERROR)
        }
    }

    private fun getMealTypes(mealType: String): List<String> {
        return when (mealType) {
            "null" -> listOf("Breakfast", "Lunch", "Dinner", "Snack", "Teatime")
            else -> listOf(mealType)
        }
    }
}
