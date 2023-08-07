package classes

import org.openrndr.color.ColorRGBa
import org.openrndr.draw.FontImageMap

data class CustomText(
    val txt: String,
    val style: ParagraphStyle
)

data class ParagraphStyle(
    val font: FontImageMap,
    val textColor: ColorRGBa,
    val leading: Double
)