package com.examplepart.foodpart

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.examplepart.foodpart.core.AppScreens
import com.examplepart.foodpart.ui.screens.food.fooddetail.FoodDetailScreen
import com.examplepart.foodpart.ui.screens.food.fullscreenimage.FullscreenImageScreen
import com.examplepart.foodpart.ui.screens.authentication.loginuser.LoginScreen
import com.examplepart.foodpart.ui.screens.authentication.registeruser.SignupScreen
import com.examplepart.foodpart.ui.screens.categories.CategoriesScreen
import com.examplepart.foodpart.ui.screens.profile.userprofile.ProfileScreen
import com.examplepart.foodpart.ui.screens.profile.archive.SavedScreen
import com.examplepart.foodpart.ui.screens.search.SearchScreen
import com.examplepart.foodpart.ui.screens.whatotcook.whattocookresult.WhatToCookResultScreen
import com.examplepart.foodpart.ui.screens.whatotcook.whatotcook.WhatToCookScreen

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
        SignupScreen(viewModel = hiltViewModel(), navController)
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