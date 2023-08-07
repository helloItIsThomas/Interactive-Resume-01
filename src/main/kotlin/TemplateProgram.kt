

import classes.MrLine
import demos.classes.Animation
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.*
import org.openrndr.math.*
import org.openrndr.shape.IntRectangle
import org.openrndr.shape.Rectangle
import java.io.File


fun main() = application {
    configure {
        val aspectRatio = 1.294
        width = 350
        height = (width * aspectRatio).toInt()
        hideWindowDecorations = true
        windowAlwaysOnTop = true
        position = IntVector2(1520,110)
        windowTransparent = true
    }


    program {
        setupKeyboardListeners()
        Global.drawer = drawer
        Global.width = width
        Global.height = height
        Global.frameCount = frameCount
        val animation = Animation()
        val myFontSize = 100.0
        val glyph = 'a'
        val myFontMap = loadFont("data/fonts/default.otf", myFontSize)
        val myFontMapTwo: FontImageMap = FontImageMap.fromFile("data/fonts/default.otf",
            myFontSize, setOf('a', 'b')
        )
//        myFontMap.texture.flipV = true
        myFontMapTwo.texture.flipV = true

        val aGlyphRect: IntRectangle? = myFontMapTwo.map['a']
        val aGlyphSize: Vector2 = Vector2(aGlyphRect!!.width.toDouble(), aGlyphRect.height.toDouble())
        val aTexturePos: Vector2 = Vector2(aGlyphRect.x.toDouble(), aGlyphRect.y.toDouble())

        // an attempt at normalizing texture sizes
        val aTextureSize = Vector2(
            myFontMapTwo.texture.width.toDouble(),
            myFontMapTwo.texture.height.toDouble()
        )
        val aTexturePosNormalized = aTexturePos / aTextureSize
        val aGlyphSizeNormalized = (aGlyphSize / aTextureSize)
        animation.loadFromJson(File("data/keyframes/keyframes-0.json"))
        val hypeLowThresh = 0.25
        val hypeHighThresh = 0.75


        val MrLine0 = MrLine()
        MrLine0.loadFromJson(File("data/keyframes/keyframes-0.json"))

        extend {
            Global.frameCount = frameCount
            MrLine0.core = frameCount.toDouble()
            MrLine0.update()
            drawer.clear(ColorRGBa.WHITE)
            animation(((frameCount * 0.007) ) % 2.0)
            MrLine0(((frameCount * 0.007) ) % 2.0)

            writerCall(sections[5])
            sections.forEach { e ->
                writerCall(e)
                if(e.id != 5){
                    writerCallSentence(e)
                }
                e.check()
                e.render(drawer)
                MrLine0.check(e)
            }

            val source = Rectangle(
                aTexturePos.x,
                aTexturePos.y,
                aGlyphSize.x,
                aGlyphSize.y
            )

            val target = Rectangle(10.0, height - 110.0, myFontSize * 0.5, myFontSize * 0.5)

            drawer.fill = null
            drawer.stroke = ColorRGBa.BLACK
            drawer.strokeWeight = 0.75
            drawer.lineSegment(
                MrLine0.drawPos,
                MrLine0.endPos
            )

            drawer.fill = null
            drawer.stroke = ColorRGBa.BLACK
            val myC = LinePath.outline.contour
            drawer.fill = ColorRGBa.BLACK
            drawer.stroke = null
            drawer.circle(myC.position(MrLine0.newPos), 5.0)

            drawer.image(myFontMapTwo.texture, source, target)
//            drawer.image(myFontMapTwo.texture)
        }
    }
}


