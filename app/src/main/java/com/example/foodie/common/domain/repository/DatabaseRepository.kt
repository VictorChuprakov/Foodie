package com.example.foodie.common.domain.repository

import com.example.foodie.common.data.room.SearchQuery
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    suspend fun insert(query: SearchQuery)
    suspend fun deleteAll()
    suspend fun deleteById(id: Long)
    fun getAll(): Flow<List<SearchQuery>>

}