import Global.drawer
import Global.frameCount
import Global.vecList
import classes.CustomText
import classes.Section
import org.openrndr.draw.Drawer
import org.openrndr.draw.writer
import org.openrndr.math.Vector2
import org.openrndr.math.mix
import org.openrndr.writer
import kotlin.math.cos
import kotlin.math.sin
import kotlin.system.measureTimeMillis
import kotlin.time.times


fun writerCallSections(section: Section, localDraw: Drawer){
    drawer.writer {
        this.box = section.thisRect
        section.allTxt.forEach { e ->
            localDraw.fontMap = e.txtStyle.font
            leading = e.txtStyle.leading
            localDraw.fill = e.txtStyle.textColor
            localDraw.stroke = null
            text(e.txtStr)
            gaplessNewLine()
            gaplessNewLine()
        }
    }
}


fun writerCallWords(section: Section, localDraw: Drawer){
    drawer.writer {
        this.box = section.thisRect
        section.allTxt.forEachIndexed { i, strBlock ->
            localDraw.fontMap = strBlock.txtStyle.font
            leading = strBlock.txtStyle.leading
            localDraw.pushStyle()
            localDraw.fill = strBlock.txtStyle.textColor
            localDraw.stroke = null

            var words = strBlock.txtStr.split(" ")


//            println("words.size in writerCallWords is " + words.size + " for " + section.id)

            words.forEachIndexed { l, word ->
                drawer.pushTransforms()
//                drawer.translate(sin(i + l + frameCount*0.005)*100.0, cos(i + l + frameCount*0.005)*100.0)
//                println(section.wordPos)
//                println(l)
//                println(l)

//                drawer.circle(section.wordPos[27], 10.0)
                text("$word ")

                drawer.popTransforms()
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
        section.allTxt.forEach { e ->
            localDraw.fontMap = e.txtStyle.font
            this.leading = e.txtStyle.leading
            localDraw.pushStyle()
            localDraw.stroke = null
            localDraw.pushTransforms()

            val words = e.txtStr.split("\\s+".toRegex())
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