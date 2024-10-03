package com.example.foodie.random.data.repository

import com.example.foodie.common.data.api.ApiError
import com.example.foodie.common.data.api.ApiInterface
import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.constans.RetrofitConstants
import com.example.foodie.common.data.mapper.toFoodResponce
import com.example.foodie.common.data.model.FoodResponce

import com.example.foodie.random.domain.repository.RandomFoodRepository
import java.io.IOException

class RandomFoodRepositoryImpl(private val apiInterface: ApiInterface) : RandomFoodRepository {

    override suspend fun getRandomFood(): State<FoodResponce> {
        return try {
            // Выполняем запрос
            val responce = apiInterface.randomFood(
                "public",
                RetrofitConstants.API_ID,
                RetrofitConstants.API_KEY,
                "LARGE",
                listOf("label", "images", "image", "uri", "totalTime", "mealType","uri"),
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
}
