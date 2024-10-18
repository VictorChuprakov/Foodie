package com.example.foodie.common.di

import android.content.Context
import com.example.foodie.common.data.dataPreference.DataPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataPreferenceModule {
    @Provides
    fun provideDataPreference(@ApplicationContext context: Context): DataPreference{
        return DataPreference(context)
    }
}