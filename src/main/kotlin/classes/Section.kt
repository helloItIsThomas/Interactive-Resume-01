package classes

import Global.globalThis
import Mouse
import generateFlock
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.color.presets.FOREST_GREEN
import org.openrndr.extra.envelopes.ADSRTracker
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
    var wordPosOverTime: List<List<Vector2>>

    var parentNumWords = mutableListOf<Int>()

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
        wordPosOverTime = generateFlock(numWords)
    }

    var isSelected = false
    var isWithin = false
    var isWithinPrev = false
    var origin = Vector2(0.0, 0.0)
    var isTriggerActive = false

    fun check() {
        isSelected = this.id == Global.selection
    }

    fun intro() {
        sectionTracker.triggerOn()
    }

    fun getDist(Mouse: Mouse) {
        isWithin = thisRect.contains(Mouse.pos)
        if(isWithin != isWithinPrev){
            if(isWithin){
                if(isSelected){
                    isWithinPrev = isWithin
                    sectionTracker.triggerOff()
                    println("trigger off")
                    Selector.updateSelection()
                }
            } else{
                isWithinPrev = isWithin
            }
        }
        // just to clarify, phaseAmt is not the thing that is directly changing the positions.
        // phaseAmt is how much mix there is between the origin position and the secondary position.
    }

    fun move(result: Double) {
//        this.phaseAmt = (Global.clock * (result)).map(0.0, 10.0, 0.0, 1.0, true)
    }

    fun render(drawer: Drawer) {
        this.phaseAmt = sectionTracker.value()
        thisRect = Rectangle(
            _x,
            _y,
            _w, // + sectionTracker.value() * thisPeakAttackLv,
            _h
        )
        drawer.stroke = ColorRGBa.FOREST_GREEN
        drawer.fill = null
//        if (this.id == 4){
            drawer.rectangle(thisRect)
//            drawer.rectangle(thisOuter)
//        }
//        drawer.circle(thisRect.corner, sectionTracker.value() * thisPeakAttackLv)
//        if (isSelected) drawer.rectangle(thisRect)
    }
}

