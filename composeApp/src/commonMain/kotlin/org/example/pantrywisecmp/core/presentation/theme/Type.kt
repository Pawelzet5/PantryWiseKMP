package org.example.pantrywisecmp.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import pantrywisecmp.composeapp.generated.resources.HostGrotesk_Bold
import pantrywisecmp.composeapp.generated.resources.HostGrotesk_Light
import pantrywisecmp.composeapp.generated.resources.HostGrotesk_Medium
import pantrywisecmp.composeapp.generated.resources.HostGrotesk_Regular
import pantrywisecmp.composeapp.generated.resources.HostGrotesk_SemiBold
import pantrywisecmp.composeapp.generated.resources.Res


val HostGrotesk @Composable get() = FontFamily(
        Font(
            resource = Res.font.HostGrotesk_Light,
            weight = FontWeight.Light
        ),
        Font(
            resource = Res.font.HostGrotesk_Regular,
            weight = FontWeight.Normal
        ),
        Font(
            resource = Res.font.HostGrotesk_Medium,
            weight = FontWeight.Medium
        ),
        Font(
            resource = Res.font.HostGrotesk_SemiBold,
            weight = FontWeight.SemiBold
        ),
        Font(
            resource = Res.font.HostGrotesk_Bold,
            weight = FontWeight.Bold
        ),
    )

// Set of Material typography styles to start with
val Typography @Composable get() = Typography(
    headlineLarge = TextStyle(
        fontFamily = HostGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = HostGrotesk,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.15.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = HostGrotesk,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    bodySmall = TextStyle(
        fontFamily = HostGrotesk,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.15.sp
    ),
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)