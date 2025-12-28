package com.moneyplusplus.design_system.theme.typography

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import money.design_system.generated.resources.Res
import money.design_system.generated.resources.Rubik_Medium
import money.design_system.generated.resources.Rubik_Regular
import money.design_system.generated.resources.Rubik_SemiBold
import org.jetbrains.compose.resources.Font

@Composable
fun createAppTypography(): Typography {
    val rubickFontFamily = FontFamily(
        Font(Res.font.Rubik_Regular, FontWeight.Normal),
        Font(Res.font.Rubik_Medium, FontWeight.Medium),
        Font(Res.font.Rubik_SemiBold, FontWeight.SemiBold)
    )
    return Typography(
        heading = Typography.Heading(
            large = TextStyle(
                fontFamily = rubickFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                lineHeight = 42.sp,
            ),
            medium = TextStyle(
                fontFamily = rubickFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                lineHeight = 36.sp,
            ),
            small = TextStyle(
                fontFamily = rubickFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                lineHeight = 30.sp,
            )
        ),
        title = Typography.Title(
            large = TextStyle(
                fontFamily = rubickFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                lineHeight = 30.sp,
            ),
            medium = TextStyle(
                fontFamily = rubickFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                lineHeight = 28.sp,
            ),
            small = TextStyle(
                fontFamily = rubickFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 24.sp,
            )
        ),
        body = Typography.Body(
            large = TextStyle(
                fontFamily = rubickFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 28.sp,
            ),
            medium = TextStyle(
                fontFamily = rubickFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
            ),
            small = TextStyle(
                fontFamily = rubickFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 22.sp,
            )
        ),
        label = Typography.Label(
            large = TextStyle(
                fontFamily = rubickFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 24.sp,
            ),
            medium = TextStyle(
                fontFamily = rubickFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 22.sp,
            ),
            small = TextStyle(
                fontFamily = rubickFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                lineHeight = 18.sp,
            ),
            xSmall = TextStyle(
                fontFamily = rubickFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                lineHeight = 14.sp,
            )
        )
    )
}