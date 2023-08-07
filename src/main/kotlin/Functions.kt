import Global.drawer
import Global.frameCount
import Global.vecList
import classes.Section
import org.openrndr.draw.writer
import org.openrndr.math.Vector2
import org.openrndr.math.mix
import org.openrndr.writer


fun writerCallSentence(section: Section){
    drawer.writer {
        this.box = section.thisRect
        section.txt.forEach{ e ->
            drawer.fontMap = e.style.font
            leading = e.style.leading
            drawer.pushStyle()
            drawer.fill = e.style.textColor
            drawer.stroke = null
            text(e.txt)
            drawer.popStyle()
            gaplessNewLine()
            gaplessNewLine()
        }
    }
}

fun writerCall(section: Section) {
    drawer.writer {
        this.box = section.thisRect
        section.txt.forEachIndexed { i, e ->
            drawer.fontMap = e.style.font
            leading = e.style.leading
            drawer.pushStyle()
            drawer.fill = e.style.textColor
            drawer.stroke = null
            drawer.pushTransforms()

            val state1 = Vector2(0.0, 0.0)
            val words = e.txt.split("\\s+".toRegex())
//                    text(words[(frameCount*0.05).toInt() % words.size])
            words.forEachIndexed { ii, word ->
                val index = (ii + frameCount*0.1) % vecList.size
                val state2 = Vector2(
                    vecList[index.toInt()].x,
                    vecList[index.toInt()].y
                )
                val moveVect = mix(state1, state2, 1.0)
//                        drawer.text(words[(frameCount*0.05).toInt() % words.size])
            }
//                    text(e.txt)
            drawer.popTransforms()
            drawer.popStyle()
            gaplessNewLine()
            gaplessNewLine()
        }
    }
}