package com.examplepart.foodpart.ui.screens.whatotcook

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.examplepart.foodpart.R

enum class RadioOption(val title: String, val icon: ImageVector) {
    OPTION_1("سخت", Icons.Default.Check),
    OPTION_2("متوسط", Icons.Default.Check),
    OPTION_3("آسان", Icons.Default.Check),
    OPTION_4("مهم نیس", Icons.Default.Check)
}