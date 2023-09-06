package com.examplepart.foodpart.ui.screens.whatotcook

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.examplepart.foodpart.R

@Composable
fun WhatToCookResultScreen() {
    Text(
        text = stringResource(R.string.picture),
        style = MaterialTheme.typography.h2,
        color = MaterialTheme.colors.onBackground,
    )
}