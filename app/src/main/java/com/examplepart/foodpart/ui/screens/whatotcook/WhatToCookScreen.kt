package com.examplepart.foodpart.ui.screens.whatotcook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.examplepart.foodpart.ui.core.AppScreens

@Composable
fun WhatToCookScreen(navController: NavController) {
    Column {
        Text(text = "What To Cook Screen", fontSize = 45.sp)
        Row {
            Button(onClick = {
                navController.navigate(AppScreens.Login.route)
            }, Modifier.weight(1f)) {
                Text("Navigate Login Screen", fontSize = 20.sp)
            }
            Button(onClick = {
                navController.navigate(AppScreens.Profile.route)
            }, Modifier.weight(1f)) {
                Text("Navigate Profile Screen", fontSize = 20.sp)
            }
            Button(
                onClick = { navController.navigate(AppScreens.Search.route) },
                Modifier.weight(1f)
            ) {
                Text("Navigate Search Screen", fontSize = 20.sp)
            }
        }
        Button(onClick = {
            navController.navigate(AppScreens.WhatToCookResult.route)
        }) {
            Text(text = "Navigate What To Cook Result Screen", fontSize = 20.sp)
        }
    }
}