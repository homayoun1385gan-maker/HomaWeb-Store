package com.homaweb.store.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val HomaPurple = Color(0xFF7138E8)
private val HomaPurpleDark = Color(0xFF4E22A7)
private val HomaBackground = Color(0xFFF8F5FF)
private val HomaText = Color(0xFF241A35)

private val HomaColorScheme = lightColorScheme(
    primary = HomaPurple,
    onPrimary = Color.White,
    secondary = HomaPurpleDark,
    background = HomaBackground,
    onBackground = HomaText,
    surface = Color.White,
    onSurface = HomaText
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = HomaColorScheme,
        content = content
    )
}
