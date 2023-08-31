package com.examplepart.foodpart.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.examplepart.foodpart.ui.core.AppScreens

@Composable
fun CategoriesScreen(navController: NavController) {
    Column {
        Text("Categories Screen", fontSize = 45.sp)
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                navController.navigate(AppScreens.WhatToCook.route)
            }, Modifier.weight(1f)) {
                Text("Navigate What To Cook Screen", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = {
                navController.navigate(AppScreens.Profile.route)
            }, Modifier.weight(1f)) {
                Text("Navigate Profile Screen", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { navController.navigate(AppScreens.Search.route) },
                Modifier.weight(1f)
            ) {
                Text("Navigate Search Screen", fontSize = 20.sp)
            }
        }
        Button(onClick = {
            navController.navigate(AppScreens.FoodDetail.route)
        }) {
            Text(text = "Navigate Food Detail Screen", fontSize = 20.sp)
        }
    }
}