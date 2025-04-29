package br.com.lucascordeiro.nexly.shared.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class NexlyTypography(

    /**
     * Large title text style.
     * Font size: 24sp
     * Font weight: Medium
     */
    val largeTitle: TextStyle = NexlyTypographyDefault.largeTitle,

    /**
     * Title text style.
     * Font size: 20sp
     * Font weight: Semibold
     */
    val title: TextStyle = NexlyTypographyDefault.title,


    /**
     * Subtitle text style.
     * Font size: 16sp
     * Font weight: Semibold
     */
    val subtitle: TextStyle = NexlyTypographyDefault.subtitle,

    /**
     * Headline text style.
     * Font size: 16sp
     * Font weight: Regular
     */
    val headline: TextStyle = NexlyTypographyDefault.headline,

    /**
     * Body text style.
     * Font size: 14sp
     * Font weight: Semibold
     */
    val body: TextStyle = NexlyTypographyDefault.body,

    /**
     * Caption text style.
     * Font size: 14sp
     * Font weight: Medium
     */
    val caption: TextStyle = NexlyTypographyDefault.caption
) {
    fun toMaterialTypography() = Typography(
        displayLarge = largeTitle,
        displayMedium = title,
        displaySmall = subtitle,
        headlineLarge = headline,
        headlineMedium = headline,
        headlineSmall = headline,
        titleLarge = largeTitle,
        titleMedium = title.copy(fontSize = 22.sp),
        titleSmall = title,
        bodyLarge = headline,
        bodyMedium = headline,
        bodySmall = body,
        labelLarge = caption,
        labelMedium = caption,
        labelSmall = caption
    )

    companion object {
        val NexlyTypographyDefault = NexlyTypography(
            largeTitle = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Font.Inter
            ),
            title = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Font.Inter
            ),
            subtitle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Font.Inter
            ),
            headline = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = Font.Inter
            ),
            body = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Font.Inter
            ),
            caption = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Font.Inter
            )
        )
    }
}
