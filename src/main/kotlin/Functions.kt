import Global.drawer
import Global.frameCount
import Global.vecList
import classes.Section
import org.openrndr.draw.Drawer
import org.openrndr.draw.writer
import org.openrndr.math.Vector2
import org.openrndr.math.mix
import org.openrndr.shape.Circle
import kotlin.math.sin


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
    var globalWordIndex = 0
    drawer.writer {
        this.box = section.thisRect
        // here, below, is where we are handling
        // a full section only through individual sub-sections.
        // so, i should go to from 0 - 3 in the case of section 4.
        // that's correct.

        section.allTxt.forEachIndexed { i, strBlock ->
            localDraw.fontMap = strBlock.txtStyle.font
            leading = strBlock.txtStyle.leading
            localDraw.pushStyle()
            localDraw.fill = strBlock.txtStyle.textColor
            localDraw.stroke = null

            val words = strBlock.txtStr.split(" ")

            words.forEachIndexed { l, word ->
                drawer.pushTransforms()

//                tempGuideCirc = Circle((_x + _w*0.5), (_y + _h*0.5), _w*0.5)

                val temp2 = Vector2(
                    section.wordPosOverTime[globalWordIndex][
                        (((Global.animation.pathSlider * 60.0)).toInt()) % 60
                    ].x - this.cursor.x + (section._x + section._w*0.5),
                    section.wordPosOverTime[globalWordIndex][
                        (((Global.animation.pathSlider * 60.0)).toInt()) % 60
                    ].y - this.cursor.y + (section._y + section._h*0.5),
                )
//                val temp2 = Vector2(
//                    section.wordPos[globalWordIndex].x - this.cursor.x,
//                    section.wordPos[globalWordIndex].y - this.cursor.y
//                )
                drawer.translate(mix(section.origin, temp2,
                    Global.animation.pathSlider
//                    1.0
//                    (frameCount*0.0075) % 1.0
                ))
                text("$word ")
                globalWordIndex++
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




fun generateFlock(numWords: Int): List<List<Vector2>> {
    val outerListSize = numWords
    val innerListSize = 60
    val moveRad = 300.0
    val flock = mutableListOf<List<Vector2>>()

    for (i in 0 until outerListSize) {
        val birdPositions = mutableListOf<Vector2>()
        for (j in 0 until innerListSize) {
            val x = ((0.5 + 0.1 * sin((i*0.038 + j*0.24) + 2 * Math.PI * j / innerListSize)).toFloat()) * moveRad - moveRad*0.5
            val y = ((0.5 + 0.1 * sin((j*0.1 + i*0.07) + 4 * Math.PI * (j) / innerListSize)).toFloat()) * moveRad - moveRad*0.5
            birdPositions.add(Vector2(x, y))
        }
        flock.add(birdPositions)
    }

    return flock
}
