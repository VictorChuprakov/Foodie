package com.example.foodie.common.data.repository

import com.example.foodie.common.data.room.dao.SearchQueryDao
import com.example.foodie.common.data.room.entities.SearchHistory
import com.example.foodie.common.domain.repository.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow

class SearchHistoryRepositoryImpl(private val searchQueryDao: SearchQueryDao):SearchHistoryRepository {
    override suspend fun addSearchQuery(query: SearchHistory) {
        searchQueryDao.insert(query)
    }

    override suspend fun getQueryByText(query: String): SearchHistory? {
        return searchQueryDao.findQuery(query)
    }

    override fun getAllQueries(): Flow<List<SearchHistory>> {
        return searchQueryDao.getAllQueries()
    }
}