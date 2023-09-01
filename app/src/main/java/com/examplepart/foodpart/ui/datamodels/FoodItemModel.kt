package com.examplepart.foodpart.ui.datamodels

import androidx.compose.runtime.mutableStateListOf

data class FoodItemModel(val name: String, val time: String)

val fakeData = mutableStateListOf(
    FoodItemModel("پیتزا", "30 دقیقه"),
    FoodItemModel("پاستا", "15 دقیقه"),
    FoodItemModel("برگر", "20 دقیقه"),
    FoodItemModel("سالاد سزار", "10 دقیقه"),
    FoodItemModel("قرمه‌سبزی", "45 دقیقه"),
    FoodItemModel("کباب کوبیده", "25 دقیقه"),
    FoodItemModel("ساندویچ تنوری", "5 دقیقه"),
    FoodItemModel("کیک شکلاتی", "40 دقیقه"),
    FoodItemModel("ماهی سرخ شده", "35 دقیقه"),
    FoodItemModel("عدسی", "30 دقیقه")
)
