package com.example.foodie.common.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "search_queries")
data class SearchQuery(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // Убедитесь, что id определен как PrimaryKey
    val query: String
)
