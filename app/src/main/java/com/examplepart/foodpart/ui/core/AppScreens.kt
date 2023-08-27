package com.examplepart.foodpart.ui.core

sealed class AppScreens(val route: String) {
    object FoodDetail : AppScreens("foodDetail")
    object FullscreenImage : AppScreens("fullscreenImage")
    object Login : AppScreens("login")
    object Signup : AppScreens("signup")
    object Categories : AppScreens("categories")
    object Profile : AppScreens("profile")
    object Saved : AppScreens("saved")
    object Search : AppScreens("search")
    object WhatToCookResult : AppScreens("whatToCookResult")
    object WhatToCook : AppScreens("whatToCook")
}
