package com.example.foodie.common.domain.repository

import com.example.foodie.common.data.room.entities.SearchHistory
import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {
    suspend fun addSearchQuery(query: SearchHistory)
    fun getAllQueries(): Flow<List<SearchHistory>>
    suspend fun getQueryByText(query: String): SearchHistory?
}