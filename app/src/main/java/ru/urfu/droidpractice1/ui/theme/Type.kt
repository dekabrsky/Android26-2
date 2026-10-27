package ru.urfu.droidpractice1.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.font.FontFamily
import ru.urfu.droidpractice1.R
import androidx.compose.ui.text.googlefonts.Font

// Set of Material typography styles to start with

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val TinosFamily = FontFamily(
    Font(googleFont = GoogleFont("Tinos"), fontProvider = provider)
)

val CalibriFamily = FontFamily(
    Font(googleFont = GoogleFont("Calibri"), fontProvider = provider)
)

val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = TinosFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = CalibriFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)