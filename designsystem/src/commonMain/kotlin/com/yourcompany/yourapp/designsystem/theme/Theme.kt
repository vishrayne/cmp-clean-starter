package com.yourcompany.yourapp.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// 1. Define Default Schemes
private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    primaryContainer = Black,
    secondary = Secondary,
    tertiary = Tertiary,
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    primaryContainer = White,
    secondary = Secondary,
    tertiary = Tertiary,
)

// 2. Define an 'expect' function to get platform-specific colors (Dynamic)
@Composable
internal expect fun getPlatformColorScheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
): androidx.compose.material3.ColorScheme?

// 3. The Public Theme Composable
@Composable
fun YourAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Android 12+ feature
    content: @Composable () -> Unit,
) {
    // Try to get platform specific scheme (Dynamic), otherwise fallback to default
    val colorScheme = getPlatformColorScheme(darkTheme, dynamicColor)
        ?: if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = YourAppTypography,
        content = content,
    )
}
