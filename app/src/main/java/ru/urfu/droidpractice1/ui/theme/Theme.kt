package ru.urfu.droidpractice1.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ArticleColors = lightColorScheme(
    primary = Color(0xFF254E41),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE5ECDD),
    onPrimaryContainer = Color(0xFF254E41),
    secondary = Color(0xFF254E41),
    secondaryContainer = Color(0xFFE5ECDD),
    onSecondaryContainer = Color(0xFF254E41),
    background = Color(0xFFFAF8F3),
    surface = Color(0xFFFAF8F3),
    onBackground = Color(0xFF202B26),
    onSurface = Color(0xFF202B26),
    onSurfaceVariant = Color(0xFF606960),
    outline = Color(0xFFB5BDB2)
)

@Composable
fun DroidPractice1Theme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = ArticleColors, typography = Typography, content = content)
}
