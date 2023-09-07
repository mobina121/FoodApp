package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun ShowError(
    errorMessage: String,
    buttonTitle: String,
    reTry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 20.dp),
            text = errorMessage,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center

        )

        CustomButton(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 130.dp),
            buttonText = buttonTitle
        ) {
            reTry()
        }
    }
}
