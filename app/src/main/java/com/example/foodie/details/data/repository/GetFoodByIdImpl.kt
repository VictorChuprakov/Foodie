package com.example.foodie.details.data.repository

import com.example.foodie.common.data.api.ApiError
import com.example.foodie.common.data.api.ApiInterface
import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.constans.RetrofitConstants
import com.example.foodie.details.data.mapper.toFoodDetails
import com.example.foodie.details.data.model.FoodDetails
import com.example.foodie.details.domain.GetFoodById
import java.io.IOException

class GetFoodByIdImpl(private val apiInterface: ApiInterface) : GetFoodById {
    override suspend fun GetFoodId(uri: String): State<FoodDetails> {
        return try {
            val responce = apiInterface.getFoodId(
                "public",
                uri,
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
                    "url"
                )
            )

            // Проверяем успешность запроса
            if (responce.isSuccessful) {
                val rawBody = responce.body()

                val body = rawBody?.toFoodDetails()

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
