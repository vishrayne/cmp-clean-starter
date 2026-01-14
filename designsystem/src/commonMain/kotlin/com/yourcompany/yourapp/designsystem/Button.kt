package com.yourcompany.yourapp.designsystem

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yourcompany.yourapp.designsystem.theme.YourAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class ButtonVariant {
    Primary,
    Secondary,
    Outline,
}

@Composable
fun Button(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: ButtonVariant = ButtonVariant.Primary,
) {
    PlatformButtonImpl(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        variant = variant,
        content = { Text(text) },
    )
}

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: ButtonVariant = ButtonVariant.Primary,
    content: @Composable RowScope.() -> Unit,
) {
    PlatformButtonImpl(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        variant = variant,
        content = content,
    )
}

/**
 * Internal contract for platform implementations.
 */
@Composable
internal expect fun PlatformButtonImpl(
    onClick: () -> Unit,
    enabled: Boolean,
    variant: ButtonVariant,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
)

// --- PREVIEWS ---

@Preview
@Composable
internal fun ButtonPreview() {
    YourAppTheme {
        Button(
            onClick = {},
            text = "Click Me (Common Preview)",
        )
    }
}
