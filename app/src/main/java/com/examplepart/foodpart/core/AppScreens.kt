package com.examplepart.foodpart.core

sealed class AppScreens(val route: String) {
    object FoodDetail : AppScreens("foodDetail?id={id}") {
        fun createRoute(id: String): String {
            return "foodDetail=$id"
        }
    }

    object FullscreenImage : AppScreens("fullscreenImage")
    object Login : AppScreens("login")
    object Signup : AppScreens("signup")
    object Categories : AppScreens("categories")
    object Profile : AppScreens("profile")
    object Saved : AppScreens("saved")
    object Search : AppScreens("search")

    object WhatToCookResult : AppScreens(
        "whatToCookResult?ingredients={ingredients}&timeLimit={timeLimit}&difficulty={difficulty}"){
        fun createRoute(
            ingredients: String,
            timeLimit: String,
            difficulty: String?
        ): String {
            return "whatToCookResult?ingredients=$ingredients&timeLimit=$timeLimit&difficulty=$difficulty"
        }
    }
    object WhatToCook : AppScreens("whatToCook")
}
