package com.examplepart.foodpart

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.examplepart.foodpart.core.AppScreens
import com.examplepart.foodpart.ui.screens.authentication.loginuser.LoginScreen
import com.examplepart.foodpart.ui.screens.authentication.registeruser.SignupScreen
import com.examplepart.foodpart.ui.screens.categories.CategoriesScreen
import com.examplepart.foodpart.ui.screens.food.fooddetail.FoodDetailScreen
import com.examplepart.foodpart.ui.screens.food.foodsbycategoryresult.FoodsByCategoryResultScreen
import com.examplepart.foodpart.ui.screens.food.foodsbymealresult.FoodsByMealResultScreen
import com.examplepart.foodpart.ui.screens.food.fullscreenimage.FullscreenImageScreen
import com.examplepart.foodpart.ui.screens.profile.archive.SavedScreen
import com.examplepart.foodpart.ui.screens.profile.userprofile.ProfileScreen
import com.examplepart.foodpart.ui.screens.search.SearchScreen
import com.examplepart.foodpart.ui.screens.whatotcook.whatotcook.WhatToCookScreen
import com.examplepart.foodpart.ui.screens.whatotcook.whattocookresult.WhatToCookResultScreen

fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    composable(AppScreens.FoodDetail.route) {
        FoodDetailScreen(hiltViewModel(), navController)
    }
    composable(AppScreens.FullscreenImage.route) {
        FullscreenImageScreen(hiltViewModel(), navController)
    }
    composable(AppScreens.Login.route) {
        LoginScreen(navController)
    }
    composable(AppScreens.Signup.route) {
        SignupScreen(viewModel = hiltViewModel(), navController)
    }
    composable(AppScreens.FoodsByCategoryResult.route) {
        FoodsByCategoryResultScreen(hiltViewModel(),navController)
    }
    composable(AppScreens.FoodsByMealResult.route) {
        FoodsByMealResultScreen(hiltViewModel(),navController)
    }
    composable(AppScreens.Categories.route) {
        CategoriesScreen(navController, hiltViewModel())
    }
    composable(AppScreens.Profile.route) {
        ProfileScreen(navController)
    }
    composable(AppScreens.Saved.route) {
        SavedScreen(hiltViewModel(), navController)
    }
    composable(AppScreens.Search.route) {
        SearchScreen(navController)
    }
    composable(AppScreens.WhatToCookResult.route) {
        WhatToCookResultScreen(hiltViewModel(), navController)
    }
    composable(AppScreens.WhatToCook.route) {
        WhatToCookScreen(hiltViewModel(), navController)
    }
}