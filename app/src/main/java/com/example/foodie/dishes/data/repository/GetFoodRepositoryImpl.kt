package com.example.foodie.dishes.data.repository

import com.example.foodie.common.data.api.ApiError
import com.example.foodie.common.data.api.ApiInterface
import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.constans.RetrofitConstants
import com.example.foodie.dishes.data.mapper.toFoodRandom
import com.example.foodie.dishes.data.model.FoodRandom
import com.example.foodie.dishes.domain.repository.GetFoodRepository
import java.io.IOException

class GetFoodRepositoryImpl(private val apiInterface: ApiInterface) : GetFoodRepository {

    override suspend fun getFood(): State<FoodRandom> {
        return try {
            // Выполняем запрос
            val responce = apiInterface.randomFood(
                "public",
                RetrofitConstants.API_ID,
                RetrofitConstants.API_KEY,
                "LARGE",
                listOf("label", "images", "image", "uri", "totalTime", "mealType"),
                true,
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
