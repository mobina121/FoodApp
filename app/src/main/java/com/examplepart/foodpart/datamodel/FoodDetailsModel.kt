package com.examplepart.foodpart.datamodel

data class FoodDetailsModel(
    val id: String,
    val name: String,
    val count: String,
    val image: String,
    val difficulty: String,
    val point: String,
    val readyTime: Int,
    val recipe: String,
    var meals: List<String>
)