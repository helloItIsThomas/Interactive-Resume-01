

import demos.classes.Animation
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.FontImageMap
import org.openrndr.draw.loadFont
import org.openrndr.draw.loadImage
import org.openrndr.extra.imageFit.imageFit
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.math.IntVector2
import org.openrndr.math.Vector4
import org.openrndr.shape.Rectangle
import org.openrndr.writer

import java.io.File



fun main() = application {
    configure {
        val aspectRatio = 1.294
        width = 350
        height = (width * aspectRatio).toInt()
        hideWindowDecorations = true
        windowAlwaysOnTop = true
        position = IntVector2(1500,110)
        windowTransparent = true
    }
    oliveProgram {
        val animation = Animation()
        val monoReg = loadFont(("data/fonts/mono-reg.otf"), width*0.013)
        val monoBold2 = loadFont(("data/fonts/mono-bold.otf"), width*0.013)
        val monoBold = loadFont(("data/fonts/mono-bold.otf"), width*0.0193)
        val maruMini = loadFont(("data/fonts/maru-mini.otf"), width*0.033)
        val resumeImg = loadImage(File("data/images/resume.jpg"))
        animation.loadFromJson(File("data/keyframes/keyframes-0.json"))

        data class ParagraphStyle(
            val font: FontImageMap,
            val textColor: ColorRGBa,
            val leading: Double // Default line spacing
        )

        val h3 = ParagraphStyle(monoReg, ColorRGBa.BLACK, 1.2)
        val h32 = ParagraphStyle(monoBold, ColorRGBa.BLACK, 1.2)
        val h31 = ParagraphStyle(monoBold2, ColorRGBa.BLACK, 1.2)
        val h1 = ParagraphStyle(maruMini, ColorRGBa.BLACK, 0.1)

        data class CustomText(
            val txt: String,
            val style: ParagraphStyle
        )

        val headBlock = mutableListOf(
            CustomText(
                "\nhello,\nit's thomas",
                h1
            ),
        )
        val infoBlock = mutableListOf(
            CustomText(
                "thomas mcelmeel",
                h3
            ),
        )
        val skillsBlock = mutableListOf(
            CustomText(
                "\nskills",
                h1
            ),
            CustomText(
                "Arduino, " +
                        "C++, CSS, Digital Photography, GitHub, HTML, JavaScript, openFrameworks, " +
                        " OPENRNDR, p5.js, Processing, Python, TouchDesigner.",
                h3
            ),
        )
        val eduBlock = mutableListOf(
            CustomText(
                "\neducation",
                h1
            ),
            CustomText(
                "College for Creative Studies",
                h32
            ),
            CustomText(
                "BFA in Communication Design",
                h3
            ),
            CustomText(
                "\nOakland Community College",
                h32
            ),
            CustomText(
                "Intro to C++",
                h3
            ),
            CustomText(
                "Intro to Java",
                h3
            ),
            CustomText(
                "Intro to HTML / CSS",
                h3
            ),
        )
        val invBlock = mutableListOf(
            CustomText(
                "\ninvolvement",
                h1
            ),
            CustomText(
                "CCS Student Activities",
                h31
            ),
            CustomText(
                "Collaborated to design, produce, and perform interactive visuals for the Embrace Your Voice Block Party.",
                h3
            ),
            CustomText(
                "\nVice-President and Co-Creator of CCS’s Creative Coding Community",
                h31
            ),
            CustomText(
                "\nDetroit Historical Society\n" +
                        "Sponsored Studio",
                h31
            ),
            CustomText(
                "Designed and installed an interactive\n" +
                        "poetry portal that was selected for an " +
                        "upcoming exhibition at the Detroit\n" +
                        "Historical Society Museum.",
                h3
            ),
            CustomText(
                "\nHonda Sponsored Studio",
                h31
            ),
            CustomText(
                "Designed and prototyped an in-vehicle wrong way alert system for hearing- impaired drivers.",
                h3
            ),
        )
        val expBlock = mutableListOf(
            CustomText(
                "\nexperience",
                h1
            ),
            CustomText(
                "RNDR",
                h32
            ),
            CustomText(
                "Creative Coding Intern",
                h31
            ),
            CustomText(
                    "Wrote small, bespoke programs with the\n" +
                            "OPENRNDR Kotlin framework for various\n" +
                            "studio projects, and assisted with the\n" +
                            "installation of RNDR’s machine-learning\n" +
                            "driven Oracle system for the Highlight\n" +
                            "Delft 2023 Festival.",
            h3
            ),
            CustomText(
                "\n2x4",
                h32
            ),
            CustomText(
                "Design Intern",
                h31
            ),
            CustomText(
                "Worked with a small team of architects\n" +
                        "and designers to program and produce\n" +
                        "a set of 67 large-scale wall graphics\n" +
                        "for YouTube.",
                h3
            ),
            CustomText(
                "\nMedia Monks",
                h32
            ),
            CustomText(
                "Design Intern",
                h31
            ),
            CustomText(
                "Worked closely with teams of creatives\n" +
                        "on identity designs, web content, and\n" +
                        "installations for brands including TikTok\n" +
                        "and Pearson.",
                h3
            ),
        )

        val recBlock = mutableListOf(
            CustomText(
                "\nrecognition",
                h1
            ),
            CustomText(
                "Imre J. Molnar Award for Visual Design Excellence",
                h31
            ),
            CustomText(
                "Design Nation Conference",
                h31
            ),
            CustomText(
                "CCS Merit + MI Competitive Scholarship",
                h31
            )
        )

        fun writerCall(textCol: Rectangle, txtList: MutableList<CustomText>){
            writer {
                this.box = textCol
                drawer.pushStyle()
                txtList.forEach { e->
                    leading = e.style.leading
                    gaplessNewLine()
                    gaplessNewLine()
                    drawer.stroke = ColorRGBa.BLACK
                    drawer.fill = null
//                    drawer.rectangle(this.box)
                    drawer.fill = e.style.textColor
                    drawer.fontMap = e.style.font
                    text(e.txt)
                }
                drawer.popStyle()
            }
        }

        val textCols = mutableListOf(
            Rectangle(width * 0.12, height * 0.05, width * 0.17, height * 0.1),
            Rectangle(width * 0.32, height * 0.05, width * 0.59, height * 0.1),
            Rectangle(width * 0.12, height * 0.22, width * 0.35, height * 0.12),
            Rectangle(width * 0.12, height * 0.353, width * 0.35, height * 0.2),
            Rectangle(width * 0.12, height * 0.575, width * 0.35, height * 0.39),
            Rectangle(width * 0.563, height * 0.22, width * 0.35, height * 0.46),
            Rectangle(width * 0.563, height * 0.693, width * 0.35, height * 0.275)
        )


        extend {
            drawer.clear(ColorRGBa.TRANSPARENT)
            drawer.imageFit(resumeImg, drawer.bounds)
            var alphaColor = ColorRGBa.fromVector(Vector4(1.0,1.0, 1.0,
                1.7)
            )
            drawer.fill = alphaColor
            drawer.rectangle(drawer.bounds)
            animation(((frameCount * 0.01) ) % 2.0)
            drawer.fill = null
            drawer.stroke = ColorRGBa.BLACK

            textCols.forEach{ e ->
//                drawer.rectangle(e)
            }

            drawer.strokeWeight = 0.75
            drawer.lineSegment(
                width * 0.12,
                height * 0.15,
                (width*0.32) + (width * 0.563),
                height * 0.15
            )
            writerCall( textCols[0], headBlock)
            writerCall( textCols[1], infoBlock)
            writerCall( textCols[2], skillsBlock)
            writerCall( textCols[3], eduBlock)
            writerCall( textCols[4], invBlock)
            writerCall( textCols[5], expBlock)
            writerCall( textCols[6], recBlock)

        }
    }
}
