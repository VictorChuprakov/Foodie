package com.example.foodie.common.data.api

import com.example.foodie.common.data.constans.RetrofitConstants
import com.example.foodie.details.data.repository.GetFoodByIdImpl
import com.example.foodie.details.domain.GetFoodById
import com.example.foodie.dishes.data.repository.GetFoodRepositoryImpl
import com.example.foodie.dishes.domain.repository.GetFoodRepository
import com.example.foodie.search.data.repository.SearchFoodRepositoryImpl
import com.example.foodie.search.domain.repository.SearchFoodRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitModule {

    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(RetrofitConstants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideApiService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    fun provideGetFoodSearch(): GetFoodRepository {
        val retrofit = provideRetrofit()
        val apiService = provideApiService(retrofit)
        return GetFoodRepositoryImpl(apiService)
    }

    fun provideGetFoodById(): GetFoodById {
        val retrofit = provideRetrofit()
        val apiService = provideApiService(retrofit)
        return GetFoodByIdImpl(apiService)
    }

    fun provideSearchFood(): SearchFoodRepository{
        val retrofit = provideRetrofit()
        val apiService = provideApiService(retrofit)
        return SearchFoodRepositoryImpl(apiService)
    }

}
