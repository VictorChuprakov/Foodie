package com.example.foodie.details.data.repository

import com.example.foodie.common.data.api.ApiError
import com.example.foodie.common.data.api.ApiInterface
import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.constans.RetrofitConstants
import com.example.foodie.common.data.mapper.toFoodResponce
import com.example.foodie.common.data.model.FoodResponce
import com.example.foodie.details.domain.repository.FoodDetailsRepository
import java.io.IOException

class FoodDetailsRepositoryImpl(private val apiInterface: ApiInterface) : FoodDetailsRepository {
    override suspend fun getFood(id: String): State<FoodResponce> {
        return try {
            val responce = apiInterface.getFoodId(
                "public",
                id,
                RetrofitConstants.API_ID,
                RetrofitConstants.API_KEY,
                listOf(
                    "label",
                    "image",
                    "images",
                    "totalNutrients",
                    "ingredientLines",
                    "totalTime",
                    "calories",
                    "mealType",
                    "totalWeight",
                    "url",
                )
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
        }    }
}
