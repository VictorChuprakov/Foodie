package com.example.foodie.search.ui

import com.example.foodie.R


enum class Category (val apiName: String, val displayNameResId: Int) {
    All("null", R.string.all_meal_type_category),
    BREAKFAST("Breakfast", R.string.breakfast_meal_type_category),
    DINNER("Dinner", R.string.dinner_meal_type_category),
    LUNCH("Lunch", R.string.lunch_meal_type_category),
    SNACK("Snack", R.string.snack_meal_type_category),
    TEATIME("Teatime", R.string.teatime_meal_type_category),
}