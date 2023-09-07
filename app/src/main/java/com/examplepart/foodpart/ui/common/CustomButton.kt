package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun CustomButton(
    buttonText: String,
    modifier: Modifier,
    buttonPressed: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Button(
            onClick = {
                buttonPressed()
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary),
            modifier = Modifier
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = buttonText,
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}


