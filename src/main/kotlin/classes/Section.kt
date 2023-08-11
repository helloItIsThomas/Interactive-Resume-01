package classes

import Global.globalThis
import Mouse
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.color.presets.FOREST_GREEN
import org.openrndr.extra.envelopes.ADSRTracker
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle
import kotlin.properties.Delegates

class Section(
    val id: Int,
    var phaseAmt: Double,
    var _x: Double,
    var _y: Double,
    var _w: Double,
    var _h: Double,
    val allTxt: MutableList<CustomText>
) {
    var sectionTracker = ADSRTracker(globalThis)
    val thisPeakAttackLv = 50.0
    val thisSustainTime = 5000
    var thisRect = Rectangle(_x, _y, _w, _h)
    var varDecay = 0.05

    var numWords by Delegates.notNull<Int>()
    var numChars by Delegates.notNull<Int>()

    init {
        if(this.id == 4) {
            this.allTxt.forEach { t ->
                val words: List<String> = t.txtStr.split(" ")
                numWords = words.size
                println(numWords)
            }
        }

        sectionTracker.attack = 1.1
        sectionTracker.decay = 0.05
        sectionTracker.sustain = 0.9
        sectionTracker.release = 0.075
        if(this.id == 4){
            println("id: " + this.id + " " +  "numWords: " + numWords)
        }
    }

    val thisOuter = thisRect.offsetEdges(thisRect.width * 0.1, thisRect.height * 0.1)
    var isSelected = false
    var isWithin = false
    var isWithinOuter = false
    var isWithinPrev = false
    var isWithinOuterPrev = false
    var origin = Vector2(0.0, 0.0)
    var isTriggerActive = false
    fun check() {
//        if(Selector.currentSection == id){
//            isSelected = true
//        }
    }

    fun getDist(Mouse: Mouse) {
        isWithin = thisRect.contains(Mouse.pos)
        isWithinOuter = thisOuter.contains(Mouse.pos)

        if (isWithinOuterPrev != isWithinOuter) {
            if(isWithinOuter && !isWithin){
                sectionTracker.triggerOff()
                isTriggerActive = false
                // Implement
                // varDecay = distance from any edge of innerRect to mouse,
                // and scale it to between 0.0 and 1.0
            }
        }

        if (isWithinPrev != isWithin) {
            isWithinPrev = isWithin
            if(!isWithin && isTriggerActive) {
//                sectionTracker.triggerOff()
//                isTriggerActive = false
            }
            if(isWithin && !isTriggerActive) {
//                sectionTracker.triggerOn()
//                isTriggerActive = true
            }
        }
        // just to clarify, phaseAmt is not the thing that is directly changing the positions.
        // phaseAmt is how much mix there is between the origin position and the secondary position.
        isSelected = isWithin
    }

    fun move(result: Double) {
//        this.phaseAmt = (Global.clock * (result)).map(0.0, 10.0, 0.0, 1.0, true)
    }

    fun render(drawer: Drawer) {
        thisRect = Rectangle(
            _x,
            _y,
            _w + sectionTracker.value() * thisPeakAttackLv,
            _h
        )
        drawer.stroke = ColorRGBa.FOREST_GREEN
        drawer.fill = null
        if (this.id == 4){
            drawer.rectangle(thisRect)
            drawer.rectangle(thisOuter)
//            println(sectionTracker.value())
        }
        drawer.circle(thisRect.corner, sectionTracker.value() * thisPeakAttackLv)
//        if (isSelected) drawer.rectangle(thisRect)
    }
}

