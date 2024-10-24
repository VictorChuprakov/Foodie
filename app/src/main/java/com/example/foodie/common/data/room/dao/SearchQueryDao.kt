package com.example.foodie.common.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodie.common.data.room.entities.SearchHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchQueryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(query: SearchHistory)

    @Query("SELECT * FROM search_history WHERE `query` = :query LIMIT 1")
    suspend fun findQuery(query: String): SearchHistory?

    @Query("SELECT * FROM search_history")
    fun getAllQueries(): Flow<List<SearchHistory>>
}