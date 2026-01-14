package com.yourcompany.yourapp.designsystem

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal actual fun PlatformButtonImpl(
    onClick: () -> Unit,
    enabled: Boolean,
    variant: ButtonVariant,
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    // Android Strategy: Use Standard Material 3, but customized for "Expressive" feel
    val colors = when (variant) {
        ButtonVariant.Primary -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        )
        ButtonVariant.Secondary -> ButtonDefaults.filledTonalButtonColors()
        ButtonVariant.Outline -> ButtonDefaults.outlinedButtonColors()
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        // Expressive M3 often uses squircle-like or larger corner radius
        shape = RoundedCornerShape(12.dp),
        content = content,
    )
}
