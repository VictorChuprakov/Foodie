package com.example.foodie.common.data.room


import androidx.room.TypeConverter
import com.example.foodie.common.data.model.Images
import com.example.foodie.common.data.model.TotalNutrients
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
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
