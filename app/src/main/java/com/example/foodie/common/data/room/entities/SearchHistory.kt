package com.example.foodie.common.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class SearchHistory(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val query: String
)
