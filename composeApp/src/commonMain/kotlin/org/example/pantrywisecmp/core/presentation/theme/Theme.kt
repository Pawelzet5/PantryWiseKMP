package org.example.pantrywisecmp.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

// --- Light Theme Colors ---
private val LightColorScheme = lightColorScheme(
    primary = Green500,
    onPrimary = White,
    primaryContainer = Green50,
    onPrimaryContainer = Green400,
    secondary = Purple500,
    onSecondary = White,
    secondaryContainer = Purple200,
    onSecondaryContainer = White,
    background = White,
    onBackground = Black,
    surface = White,
    surfaceVariant = Black,
    surfaceContainerLow = Gray100,
    onSurface = Black,
    error = Red500,
    onError = White,
    errorContainer = Red100,
    onErrorContainer = Red700
)

@Composable
fun PantryWiseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> LightColorScheme //TODO: Implement DarkColorScheme when designed
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}