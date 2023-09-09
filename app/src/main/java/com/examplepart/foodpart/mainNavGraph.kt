package com.examplepart.foodpart

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.examplepart.foodpart.ui.core.AppScreens
import com.examplepart.foodpart.ui.screens.food.FoodDetailScreen
import com.examplepart.foodpart.ui.screens.food.FullscreenImageScreen
import com.examplepart.foodpart.ui.screens.login.LoginScreen
import com.examplepart.foodpart.ui.screens.login.SignupScreen
import com.examplepart.foodpart.ui.screens.main.CategoriesScreen
import com.examplepart.foodpart.ui.screens.profile.ProfileScreen
import com.examplepart.foodpart.ui.screens.profile.SavedScreen
import com.examplepart.foodpart.ui.screens.search.SearchScreen
import com.examplepart.foodpart.ui.screens.whatotcook.WhatToCookResultScreen
import com.examplepart.foodpart.ui.screens.whatotcook.WhatToCookScreen

fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    composable(
        route = AppScreens.FoodDetail.route,
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) {
        val id = it.arguments?.getString("id")!!
        FoodDetailScreen(navController, id)
    }
    composable(AppScreens.FullscreenImage.route) {
        FullscreenImageScreen(navController)
    }
    composable(AppScreens.Login.route) {
        LoginScreen(navController)
    }
    composable(AppScreens.Signup.route) {
        SignupScreen(navController)
    }
    composable(AppScreens.Categories.route) {
        CategoriesScreen(navController)
    }
    composable(AppScreens.Profile.route) {
        ProfileScreen(navController)
    }
    composable(AppScreens.Saved.route) {
        SavedScreen()
    }
    composable(AppScreens.Search.route) {
        SearchScreen(navController)
    }
    composable(AppScreens.WhatToCookResult.route) {
        WhatToCookResultScreen(navController)
    }
    composable(AppScreens.WhatToCook.route) {
        WhatToCookScreen(navController)
    }
}