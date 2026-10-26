package ru.urfu.droidpractice1.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkSecondary,
    tertiary = DarkTertiary,
    background = SpaceBlack,
    surface = Color(0xFF161B22),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = StarWhite,
    onSurface = StarWhite,
    surfaceVariant = Color(0xFF21262D)
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    secondary = LightSecondary,
    tertiary = LightTertiary,
    background = Color(0xFFF8F9FA),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.Black,
    onBackground = SpaceBlack,
    onSurface = SpaceBlack,
    surfaceVariant = Color(0xFFE9ECEF)
)

@Composable
fun DroidPractice1Theme(
    darkTheme: Boolean = true, // Force dark theme for space vibe
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme // Always use dark for awesome space look

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}