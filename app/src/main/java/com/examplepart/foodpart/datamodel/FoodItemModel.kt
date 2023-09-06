package com.examplepart.foodpart.datamodel

import androidx.compose.runtime.mutableStateListOf

data class FoodItemModel(val id: String, val name: String, val time: String, val imageUrl: String)

val fakeData = mutableStateListOf(
    FoodItemModel("1","پیتزا", "30 دقیقه",""),
    FoodItemModel("1","پاستا", "15 دقیقه",""),
    FoodItemModel("1","برگر", "20 دقیقه",""),
    FoodItemModel("1","سالاد سزار", "10 دقیقه",""),
    FoodItemModel("1","قرمه‌سبزی", "45 دقیقه",""),
    FoodItemModel("1","کباب کوبیده", "25 دقیقه",""),
    FoodItemModel("1","ساندویچ تنوری", "5 دقیقه",""),
    FoodItemModel("1","کیک شکلاتی", "40 دقیقه",""),
    FoodItemModel("1","ماهی سرخ شده", "35 دقیقه",""),
    FoodItemModel("1","عدسی", "30 دقیقه","")
)
