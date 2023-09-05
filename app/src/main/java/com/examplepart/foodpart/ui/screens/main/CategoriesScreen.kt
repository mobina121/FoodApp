package com.examplepart.foodpart.ui.screens.main

import com.examplepart.foodpart.ui.common.FoodPartAppBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.ui.core.AppScreens

@Composable
fun CategoriesScreen(navController: NavController) {
    Column {
        FoodPartAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            title = stringResource(id = R.string.food_details),
            showStartIcon = true,
            showEndIcon = true,
            startIcon = {
                Icon(
                    modifier = Modifier.padding(horizontal = 8.dp).size(14.dp),
                    painter = painterResource(id = R.drawable.ic_appbar_start),
                    contentDescription = "start icon",
                    tint = MaterialTheme.colors.onBackground
                )
            },
            endIcon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_action),
                    contentDescription = "end icon",
                    tint = MaterialTheme.colors.onBackground
                )
            }
        )





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
//                navController.navigate(AppScreens.Profile.route)
                navController.navigate(AppScreens.Login.route)

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