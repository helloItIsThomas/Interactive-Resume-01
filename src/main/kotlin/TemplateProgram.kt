

import Global.animation
import Global.tracker
import classes.MrLine
import classes.ParagraphStyle
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.loadFont
import org.openrndr.extra.envelopes.ADSRTracker
import org.openrndr.math.IntVector2
import org.openrndr.math.Vector2
import java.io.File
import kotlin.system.measureTimeMillis

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
        Global.monoReg = loadFont(("data/fonts/mono-reg.otf"), width*0.013)
        Global.monoBold2 = loadFont(("data/fonts/mono-bold.otf"), width*0.013)
        Global.monoBold = loadFont(("data/fonts/mono-bold.otf"), width*0.0193)
        Global.maruMini = loadFont(("data/fonts/maru-mini.otf"), width*0.033)
        Global.vecList = listOf(Vector2(306.888437030501, 200.0))
        Global.h3 = ParagraphStyle(Global.monoReg, ColorRGBa.BLACK, 1.2)
        Global.h32 = ParagraphStyle(Global.monoBold, ColorRGBa.BLACK, 1.2)
        Global.h31 = ParagraphStyle(Global.monoBold2, ColorRGBa.BLACK, 1.2)
        Global.h1 = ParagraphStyle(Global.maruMini, ColorRGBa.BLACK, 0.1)
        Global.numSections = 9


        val MrLine0 = MrLine()
        MrLine0.loadFromJson(File("data/keyframes/keyframes-0.json"))
        animation.loadFromJson(File("data/keyframes/keyframes-0.json"))

        Global.globalThis = this
        tracker = ADSRTracker(this)
        tracker.attack = 0.1
//        Global.peakAttackLv = 50.0
        tracker.decay = 0.05
//        Global.sustainTime = 5000
        tracker.sustain = 0.9
        tracker.release = 0.1

//        extend(ScreenRecorder()) {
//            contentScale = 2.0
//            frameRate = 120
//            maximumDuration =  10.0
//        }

        extend {
            if(frameCount == 0){
                Selector.updateSelection()
                println("initial sendRandomInt() fired")
            }
            Global.frameCount = frameCount
            Global.clock = (Global.frameCount*0.05)
            MrLine0.core = frameCount.toDouble()
            MrLine0.update()
            drawer.clear(ColorRGBa.WHITE)
            animation(((frameCount * 0.002) ) % 1.0)
            MrLine0(((frameCount * 0.007) ) % 2.0)
            Mouse.pos = mouse.position
            drawer.circle(Mouse.pos, Mouse.rad)

            drawer.fill = ColorRGBa.BLACK


//            drawer.circle(sections[4].tempGuideCirc)
//            sections[4].wordPos.forEach { c ->
//                drawer.circle(c.x, c.y, 5.0)
//            }

//            writerCallWords(sections[4], drawer)
//            sections[4].check()
//            sections[4].render(drawer)
//            sections[4].getDist(Mouse)

            val elapsedTime = measureTimeMillis {
                sections.forEach { e ->
//                    if(e.id != 4){
//                        writerCallSections(e, drawer)
//                    }
                    writerCallWords(e, drawer)

//                    writerCallChars(e, drawer)
                    e.getDist(Mouse)
                    e.check()
                    e.render(drawer)
                    // if I set seek to fire once with a trigger,
                    // then I can call it outside the loop,
                    // only when the condition is met.
                    MrLine0.seek()
                }
            }

//            println(elapsedTime)

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

        }
    }
}


