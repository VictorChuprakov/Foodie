package com.example.foodie.common.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchQueryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(query: SearchQuery)

    @Query("SELECT * FROM search_queries WHERE `query` = :query LIMIT 1")
    suspend fun findQuery(query: String): SearchQuery?

    @Query("SELECT * FROM search_queries")
    fun getAllQueries(): Flow<List<SearchQuery>>
}
