package com.example.foodie.common.di

import com.example.foodie.common.data.api.ApiInterface
import com.example.foodie.common.data.constans.RetrofitConstants
import com.example.foodie.details.data.repository.FoodDetailsRepositoryImpl
import com.example.foodie.details.domain.repository.FoodDetailsRepository
import com.example.foodie.dishes.data.repository.FoodRepositoryImpl
import com.example.foodie.dishes.domain.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
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

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    fun provideGetFoodSearch(apiInterface: ApiInterface): FoodRepository {
        return FoodRepositoryImpl(apiInterface)
    }

    @Provides
    fun provideGetFoodById(apiInterface: ApiInterface): FoodDetailsRepository {
        return FoodDetailsRepositoryImpl(apiInterface)
    }

}
