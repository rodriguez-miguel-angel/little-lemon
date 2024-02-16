package com.littlelemon.littlelemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.littlelemon.littlelemon.R


//val LittleLemonFontFamily = FontFamily(
//    Font(R.font, FontWeight.Light),
//    Font(R.font.firasans_regular, FontWeight.Normal),
//)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
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

object LittleLemonType {

    //private val FontFamily family = new FontFamily.Builder(new Font.Builder("karla_regular.ttf").build();

    val KarlaFontFamily = FontFamily(
        Font(R.font.karla_regular, FontWeight.Normal),
        Font(R.font.karla_medium, FontWeight.Medium),
        Font(R.font.karla_bold, FontWeight.Bold),
        Font(R.font.karla_extra_bold, FontWeight.ExtraBold),
    )

    val MarkaziTextFontFamily = FontFamily(
        Font(R.font.markazi_text_regular, FontWeight.Normal),
        Font(R.font.markazi_text_medium, FontWeight.Medium),
    )

    val displayTitle = TextStyle(
    fontFamily = MarkaziTextFontFamily,    // Markazi Text
    fontSize = 64.sp,
    fontWeight = FontWeight.Medium,
    color = LittleLemonColor.yellow
    )
    val subtitle = TextStyle(
    fontFamily = MarkaziTextFontFamily,    // Markazi Text
    fontSize = 40.sp,
    fontWeight = FontWeight.W400,
    color = LittleLemonColor.yellow
    )
    val leadText = TextStyle(
        fontFamily = KarlaFontFamily,    // Karla
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        color = LittleLemonColor.black
    )
    val sectionTitle = TextStyle(
        fontFamily = KarlaFontFamily,    // Karla. UPPERCASE.
        fontSize = 20.sp,
        fontWeight = FontWeight.W800,
        color = LittleLemonColor.black,
    )
    val sectionCategories = TextStyle(
        fontFamily = KarlaFontFamily,    // Karla.
        fontSize = 16.sp,
        fontWeight = FontWeight.W800,
        color = LittleLemonColor.black,
    )
    val cardTitle = TextStyle(
        fontFamily = KarlaFontFamily,    // Karla
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = LittleLemonColor.black
    )
    val paragraphText = TextStyle(
        fontFamily = KarlaFontFamily,    // Karla
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 1.5.sp,
        color = LittleLemonColor.black,

    )// Karla. Regular(400). 16pt. 1.5 height. Max 65 characters per line.
    val highlightText = TextStyle(
        fontFamily = KarlaFontFamily,    // Karla
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = LittleLemonColor.black
    ) //[[[e.g., price]]]: Karla. Medium. 16pt


}


