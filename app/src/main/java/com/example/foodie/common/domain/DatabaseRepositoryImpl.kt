package com.example.foodie.common.domain

import com.example.foodie.common.data.room.SearchQuery
import com.example.foodie.common.data.room.SearchQueryDao
import com.example.foodie.common.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow

class DatabaseRepositoryImpl(private val searchQueryDao: SearchQueryDao) : DatabaseRepository {
    override suspend fun insert(query: SearchQuery) {
        searchQueryDao.insert(query)
    }

    override suspend fun deleteAll() {
        searchQueryDao.deleteAll()
    }

    override suspend fun deleteById(id: Long) {
        searchQueryDao.deleteById(id)
    }

    override fun getAll(): Flow<List<SearchQuery>> {
        return searchQueryDao.getAllQueries()
    }
}
