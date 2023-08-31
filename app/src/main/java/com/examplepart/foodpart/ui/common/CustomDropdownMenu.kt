package com.examplepart.foodpart.ui.common

import androidx.compose.material.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.examplepart.foodpart.R

@Composable
fun CustomDropdownMenu(isDropDownMenuShowing: Boolean) {
    var isDropDownMenuShowing1 = isDropDownMenuShowing
    DropdownMenu(
        expanded = isDropDownMenuShowing1,
        onDismissRequest = {
            isDropDownMenuShowing1 = false
        }) {
        CustomDropdownMenuItem(
            title = stringResource(R.string.report),
            iconId = R.drawable.ic_report,
            onClicked = {})
        CustomDropdownMenuItem(
            title = stringResource(R.string.send),
            iconId = R.drawable.ic_share,
            onClicked = {})
        CustomDropdownMenuItem(
            title = stringResource(R.string.save),
            iconId = R.drawable.ic_bookmark,
            onClicked = {})
    }
}