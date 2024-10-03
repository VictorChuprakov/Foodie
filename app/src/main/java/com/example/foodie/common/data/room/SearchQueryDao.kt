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

    @Query("SELECT * FROM search_queries")
    fun getAllQueries(): Flow<List<SearchQuery>>

    @Query("DELETE FROM search_queries") // Метод для удаления всех записей
    suspend fun deleteAll() // Удаление всех записей

    @Query("DELETE FROM search_queries WHERE id = :id")
    suspend fun deleteById(id: Long) // Удаление по идентификатору
}
