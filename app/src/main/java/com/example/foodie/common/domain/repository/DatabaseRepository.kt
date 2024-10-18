package com.example.foodie.common.domain.repository

import com.example.foodie.common.data.room.SearchQuery
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    suspend fun insert(query: SearchQuery)
    suspend fun findQuery(query: String): SearchQuery?
    fun getAll(): Flow<List<SearchQuery>>
}