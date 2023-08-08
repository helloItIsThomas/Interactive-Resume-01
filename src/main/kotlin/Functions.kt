import Global.drawer
import Global.frameCount
import Global.vecList
import classes.Section
import org.openrndr.draw.Drawer
import org.openrndr.draw.writer
import org.openrndr.math.Vector2
import org.openrndr.math.mix
import org.openrndr.writer
import kotlin.time.times


fun writerCallSections(section: Section, localDraw: Drawer){
    drawer.writer {
        this.box = section.thisRect
        section.txt.forEach { e ->
            localDraw.fontMap = e.style.font
            leading = e.style.leading
            localDraw.fill = e.style.textColor
            localDraw.stroke = null
            text(e.txt)
            gaplessNewLine()
            gaplessNewLine()
        }
    }
}

fun writerCallWords(section: Section, localDraw: Drawer){
    drawer.writer {
        this.box = section.thisRect
        section.txt.forEach{ e ->
            localDraw.fontMap = e.style.font
            leading = e.style.leading
            localDraw.pushStyle()
            localDraw.fill = e.style.textColor
            localDraw.stroke = null
            val words = e.txt.split("\\s+".toRegex())


            words.forEachIndexed { i, word ->
                if(section.id == 4) {
                    localDraw.pushTransforms()
                    localDraw.translate(mix(section.origin, VecList.vecList[0] * 0.5, section.phaseAmt))
                    text(word)
                    localDraw.popTransforms()
                } else text(word)
            }

            localDraw.popStyle()
            gaplessNewLine()
            gaplessNewLine()
        }
    }
}

fun writerCallChars(section: Section, localDraw: Drawer) {
    localDraw.writer {
        this.box = section.thisRect
        section.txt.forEach { e ->
            localDraw.fontMap = e.style.font
            this.leading = e.style.leading
            localDraw.pushStyle()
            localDraw.stroke = null
            localDraw.pushTransforms()

            val words = e.txt.split("\\s+".toRegex())
//            text(words[(frameCount*0.05).toInt() % words.size])
            words.forEachIndexed { ii, word ->
                val index = (ii + frameCount*0.1) % vecList.size
                val state2 = Vector2(
                    vecList[index.toInt()].x,
                    vecList[index.toInt()].y
                )
                word.forEach { char ->
                    this.text(char.toString())
                }
//
//                val moveVect = mix(state1, state2, 1.0)
//                drawer.text(words[(frameCount*0.05).toInt() % words.size])
            }
            localDraw.popTransforms()
            localDraw.popStyle()
            gaplessNewLine()
            gaplessNewLine()
        }
    }
}