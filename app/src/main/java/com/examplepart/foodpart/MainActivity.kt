package com.examplepart.foodpart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.examplepart.foodpart.ui.core.AppScreens
import com.examplepart.foodpart.ui.theme.FoodPartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            val navigationItems = listOf(
                AppScreenItem(
                    AppScreens.Categories,
                    stringResource(R.string.categories),
                    R.drawable.ic_category
                ),
                AppScreenItem(
                    AppScreens.WhatToCook,
                    stringResource(R.string.whatToCook),
                    R.drawable.ic_restaurant_menu
                ),
                AppScreenItem(
                    AppScreens.Search,
                    stringResource(R.string.search),
                    R.drawable.ic_search
                ),
                AppScreenItem(
                    AppScreens.Profile,
                    stringResource(R.string.profile),
                    R.drawable.ic_profile
                )
            )

            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            FoodPartTheme {
                Scaffold(
                    bottomBar = {
                        if (currentDestination?.route in navigationItems.map { it.screen.route }) {
                            BottomNavigation(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp)
                                    .clip(
                                        MaterialTheme.shapes.large.copy(
                                            bottomEnd = CornerSize(0.dp),
                                            bottomStart = CornerSize(0.dp)
                                        )
                                    ),
                                backgroundColor = MaterialTheme.colors.secondary

                            ) {
                                navigationItems.forEach { screen ->
                                    BottomNavigationItem(
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.screen.route } == true,
                                        onClick = {
                                            navController.navigate(screen.screen.route) {
                                                popUpTo(AppScreens.Categories.route) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        icon = {
                                            Icon(
                                                modifier = Modifier.padding(6.dp),
                                                painter = painterResource(id = screen.iconResId),
                                                contentDescription = null
                                            )
                                        },
                                        label = {
                                            Text(
                                                modifier = Modifier.padding(bottom = 10.dp),
                                                text = screen.label,
                                                style = MaterialTheme.typography.subtitle1
                                            )
                                        },
                                        alwaysShowLabel = true,
                                        selectedContentColor = MaterialTheme.colors.primary,
                                        unselectedContentColor = White,
                                        modifier = Modifier
                                            .padding(bottom = 10.dp)
                                            .background(
                                                color = MaterialTheme.colors.secondary
                                            )
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = AppScreens.Categories.route,
                        Modifier.padding(innerPadding)
                    ) {
                        mainNavGraph(navController)
                    }
                }
            }
        }
    }
}
