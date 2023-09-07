package com.examplepart.foodpart.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.examplepart.foodpart.R
import com.examplepart.foodpart.ui.common.FoodPartAppBar

@Composable
fun ProfileScreen(navController: NavController) {
    ProfileScreenContent()
}

@Composable
private fun ProfileScreenContent(
) {

    Scaffold(
        topBar = {
            FoodPartAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 0.dp),
                title = stringResource(id = R.string.profile),
                showStartIcon = false,
                showEndIcon = false,
            )
        },
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    backgroundColor = Color.DarkGray,
                    contentColor = MaterialTheme.colors.onBackground,
                    snackbarData = data
                )
            }
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp),

            ) {
        }
    }

}