package com.examplepart.foodpart.ui.theme
import android.app.Activity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val lightColorPalette = lightColors(

    primary = Primary,
    secondary = Secondary,
    background = Background,
    onBackground = OnBackground,
    surface = Surface,
    onSurface = OnSurface

)

@Composable
fun FoodPartTheme(
    content: @Composable () -> Unit
) {
    val colors = lightColorPalette

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.background.toArgb()
            window.navigationBarColor = colors.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = true
        }
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
