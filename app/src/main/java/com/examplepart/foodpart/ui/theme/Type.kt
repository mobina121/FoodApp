package com.examplepart.foodpart.ui.theme
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.examplepart.foodpart.R

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.iranyekan_mobile_extra_bold)),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp
    ),
    h2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.iranyekan_mobile_bold)),
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    h3 = TextStyle(
        fontFamily = FontFamily(Font(R.font.iranyekan_mobile_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.iranyekan_mobile_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily(Font(R.font.iranyekan_mobile_extra_bold)),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily(Font(R.font.iranyekan_mobile_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )

)
