package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomDropdownMenuItem(title: String, iconId: Int, onClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                onClicked()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 16.dp, end = 8.dp)
                .size(24.dp),
            painter = painterResource(id = iconId),//R.drawable.ic_report
            contentDescription = "arrow forward icon",
            tint = MaterialTheme.colors.onBackground
        )

        Text(
            modifier = Modifier.weight(1f),
            text = title,//stringResource(R.string.report),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onBackground,
        )
    }
}
