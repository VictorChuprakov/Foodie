package com.example.foodie.common.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.foodie.common.data.model.Images
import com.example.foodie.common.data.model.TotalNutrients
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Entity(tableName = "search_queries")
data class SearchQuery(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // Убедитесь, что id определен как PrimaryKey
    val query: String
)

@Entity(tableName = "favorite")
data class FavoriteRecipes(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val calories: Double,
    val image: String,
    val images: Images,
    val label: String,
    val mealType: List<String>, // Лучше использовать String, если планируете хранить в виде текста
    val totalTime: Double,
    val ingredientLines: List<String>, // Лучше использовать String, если планируете хранить в виде текста
    val totalNutrients:TotalNutrients,
    val totalWeight: Double,
    val url: String,
    val uri: String
)


class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromTotalNutrients(totalNutrients: TotalNutrients): String {
        return gson.toJson(totalNutrients)
    }

    @TypeConverter
    fun toTotalNutrients(data: String): TotalNutrients {
        val listType = object : TypeToken<TotalNutrients>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(data: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(data, listType)
    }

    // Добавляем конвертер для Images
    @TypeConverter
    fun fromImages(images: Images): String {
        return gson.toJson(images)
    }

    @TypeConverter
    fun toImages(data: String): Images {
        val listType = object : TypeToken<Images>() {}.type
        return gson.fromJson(data, listType)
    }
}
