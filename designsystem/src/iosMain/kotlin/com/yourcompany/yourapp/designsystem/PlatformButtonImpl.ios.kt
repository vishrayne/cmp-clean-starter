package com.yourcompany.yourapp.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
@Composable
internal actual fun PlatformButtonImpl(
    onClick: () -> Unit,
    enabled: Boolean,
    variant: ButtonVariant,
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    // 1. Interaction Source (For "Press" effect simulation)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // 2. iOS "Glass" Logic (Pure Compose)
    // We simulate glass by using a low-alpha background + a subtle border.
    // We adjust opacity based on "Press" state to mimic iOS touch feedback.

    val baseColor = when (variant) {
        ButtonVariant.Primary -> MaterialTheme.colorScheme.primary
        ButtonVariant.Secondary -> MaterialTheme.colorScheme.secondaryContainer
        // For "Outline", we use a very faint white/surface to catch touches
        ButtonVariant.Outline -> Color.Transparent
    }

    // iOS buttons usually fade opacity on press, rather than showing a ripple
    val containerAlpha = if (variant == ButtonVariant.Primary) {
        if (isPressed) 0.7f else 1.0f // Solid primary that fades
    } else {
        // For "Glassy" secondary buttons
        if (isPressed) 0.5f else 0.15f
    }

    val containerColor = if (variant == ButtonVariant.Outline) {
        Color.Transparent
    } else {
        baseColor.copy(alpha = containerAlpha)
    }

    val contentColor = when (variant) {
        ButtonVariant.Primary -> MaterialTheme.colorScheme.onPrimary
        ButtonVariant.Secondary -> MaterialTheme.colorScheme.onSecondaryContainer
        ButtonVariant.Outline -> MaterialTheme.colorScheme.primary
    }

    val borderColor = if (variant == ButtonVariant.Outline) {
        MaterialTheme.colorScheme.outline.copy(alpha = if (isPressed) 0.5f else 1.0f)
    } else {
        Color.Transparent
    }

    // 3. The UI Structure
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(containerColor)
            .border(
                width = 1.dp,
                brush = SolidColor(borderColor),
                shape = RoundedCornerShape(12.dp),
            )
            .clickable(
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null, // Disable Material Ripple (Ripple is not very iOS-like)
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.labelLarge) {
                // If the button is pressed, we also fade the text slightly for that native feel
                val animatedContentColor = contentColor.copy(
                    alpha = if (isPressed && variant != ButtonVariant.Primary) 0.6f else 1f,
                )

                CompositionLocalProvider(LocalContentColor provides animatedContentColor) {
                    content()
                }
            }
        }
    }
}
