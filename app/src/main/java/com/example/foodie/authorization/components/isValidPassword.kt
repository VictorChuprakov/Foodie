package com.example.foodie.authorization.components

fun isValidPassword(password: String): Boolean{
    return password.length >= 8
}
