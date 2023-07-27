

import demos.classes.Animation
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.loadFont
import org.openrndr.draw.loadImage
import org.openrndr.extra.imageFit.imageFit
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.extra.shapes.grid
import org.openrndr.math.IntVector2
import org.openrndr.math.Vector4
import org.openrndr.shape.Rectangle
import org.openrndr.writer
import java.io.File

val skillsText = "Arduino, C++, CSS, Digital Photography,GitHub, HTML, JavaScript, openFrameworks, OPENRNDR,p5.js, Processing, Python, TouchDesigner."


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
        val h3 = loadFont(("data/fonts/mono-med.otf"), width*0.02)
        val resumeImg = loadImage(File("data/images/resume.jpg"))
        animation.loadFromJson(File("data/keyframes/keyframes-0.json"))
        val colCount = 2
        val rowCount = 2
        val marginX = 40.0
        val marginY = 100.0
        val gutterX = 20.0
        val gutterY = 0.0

        fun writerCall(textCol: Rectangle){
            writer {
                drawer.pushStyle()
                drawer.stroke = ColorRGBa.BLACK
                this.box = textCol
                drawer.rectangle(this.box)
                drawer.fill = ColorRGBa.BLACK
                drawer.fontMap = h3
                text(" ")
                gaplessNewLine()
                text(skillsText)
                drawer.popStyle()
            }
        }

        val textCols = mutableListOf(
            Rectangle(width * 0.12, height * 0.05, width * 0.79, height * 0.1),
            Rectangle(width * 0.12, height * 0.22, width * 0.35, height * 0.12),
            Rectangle(width * 0.12, height * 0.353, width * 0.35, height * 0.2),
            Rectangle(width * 0.12, height * 0.575, width * 0.35, height * 0.39),
            Rectangle(width * 0.563, height * 0.22, width * 0.35, height * 0.46),
            Rectangle(width * 0.563, height * 0.693, width * 0.35, height * 0.275)
        )


        extend {
            drawer.clear(ColorRGBa.TRANSPARENT)
            drawer.imageFit(resumeImg, drawer.bounds)
            var alphaColor = ColorRGBa.fromVector(Vector4(1.0,1.0, 1.0, 0.7))
            drawer.fill = alphaColor
            drawer.rectangle(drawer.bounds)
            animation(((frameCount * 0.01) ) % 2.0)
            drawer.fill = null
            drawer.stroke = ColorRGBa.BLACK

            textCols.forEach {
                writerCall( it )
            }
        }
    }
}
