package com.yourcompany.yourapp.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
internal actual fun getPlatformColorScheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
): ColorScheme? {
    // iOS doesn't support Material Dynamic Color (Wallpaper extraction) natively
    return null
}
