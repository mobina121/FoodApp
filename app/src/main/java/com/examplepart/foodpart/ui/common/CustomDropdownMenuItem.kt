package com.examplepart.foodpart.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomDropdownMenuItem(title: String, iconId: Int, onClicked: (Int) -> Unit) {
    DropdownMenuItem(onClick = {}) {
        Row(
            modifier = Modifier.width(150.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = {
                onClicked(0)
            }) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = iconId),//R.drawable.ic_report
                    contentDescription = "arrow forward icon",
                    tint = MaterialTheme.colors.onBackground
                )
            }
            Text(
                modifier = Modifier.weight(1f),
                text = title,//stringResource(R.string.report),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
            )
        }
    }
}
