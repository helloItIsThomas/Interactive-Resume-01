package classes

import Global.globalThis
import Mouse
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.color.presets.FOREST_GREEN
import org.openrndr.extra.envelopes.ADSRTracker
import org.openrndr.extra.noise.random
import org.openrndr.math.Vector2
import org.openrndr.shape.Circle
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

    var wordPos = mutableListOf<Vector2>()

    var parentNumWords = mutableListOf<Int>()
    // we are never not drawing all the text in a given section at once.
    // so we should be able to have one numWords variable.
    var numWords = 0
    var numChars by Delegates.notNull<Int>()
    var tempGuideCirc: Circle

    init {
        this.allTxt.forEach { t ->
            val words: List<String> = t.txtStr.split(" ")
            parentNumWords.add(words.size)
            numWords += words.size
        }

        tempGuideCirc = Circle((_x + _w*0.5), (_y + _h*0.5), _w*0.5)

        wordPos = tempGuideCirc.contour.equidistantPositions(numWords).toMutableList()

        sectionTracker.attack = 1.1
        sectionTracker.decay = 0.05
        sectionTracker.sustain = 0.9
        sectionTracker.release = 0.075

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
//        println("wordPos.size for " +  this.id + " is " + wordPos.size)
//        println("numWords count for " +  this.id + " is " + numWords)
    }

    fun getDist(Mouse: Mouse) {
        isWithin = thisRect.contains(Mouse.pos)
        if(isWithin != isWithinPrev){
            if(isWithin){
                isWithinPrev = isWithin
                sectionTracker.triggerOn()
            } else{
                isWithinPrev = isWithin
                sectionTracker.triggerOff()
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
//            drawer.rectangle(thisRect)
//            drawer.rectangle(thisOuter)
        }
//        drawer.circle(thisRect.corner, sectionTracker.value() * thisPeakAttackLv)
//        if (isSelected) drawer.rectangle(thisRect)
    }
}

