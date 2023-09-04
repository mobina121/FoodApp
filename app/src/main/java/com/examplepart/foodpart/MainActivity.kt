package com.examplepart.foodpart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.examplepart.foodpart.ui.core.AppScreens
import com.examplepart.foodpart.ui.theme.FoodPartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {

            val screens = listOf(
                Screen(AppScreens.WhatToCook.route, R.drawable.ic_restaurant_menu),
                Screen(AppScreens.Categories.route, R.drawable.ic_category),
                Screen(AppScreens.Search.route, R.drawable.ic_search),
                Screen(AppScreens.Profile.route, R.drawable.ic_profile)
            )

            FoodPartTheme{
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = AppScreens.Categories.route) {
                    mainNavGraph(navController)
                }
            }
        }
    }


}
