package com.example.foodie.search.data.repository

import com.example.foodie.common.data.api.ApiError
import com.example.foodie.common.data.api.ApiInterface
import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.constans.RetrofitConstants
import com.example.foodie.dishes.data.mapper.toFoodRandom
import com.example.foodie.dishes.data.model.FoodRandom
import com.example.foodie.search.domain.repository.SearchFoodRepository
import java.io.IOException

class SearchFoodRepositoryImpl(private val apiInterface: ApiInterface) : SearchFoodRepository {
    override suspend fun getSearchFood(): State<FoodRandom> {
        return try {
            // Выполняем запрос
            val responce = apiInterface.searchFood(
                "public",
                RetrofitConstants.API_ID,
                RetrofitConstants.API_KEY,
                "chiken",
                "LARGE",
                listOf("label", "images", "image", "uri", "totalTime", "mealType"),
            )

            // Проверяем успешность запроса
            if (responce.isSuccessful) {
                val rawBody = responce.body()
                val body = rawBody?.toFoodRandom()
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
}
