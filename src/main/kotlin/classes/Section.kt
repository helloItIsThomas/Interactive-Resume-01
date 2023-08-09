package classes

import Global.globalThis
import Mouse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.color.presets.FOREST_GREEN
import org.openrndr.extra.envelopes.ADSRTracker
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle

class Section(
    val id: Int,
    var phaseAmt: Double,
    var _x: Double,
    var _y: Double,
    var _w: Double,
    var _h: Double,
    val txt: MutableList<CustomText>
) {

    var sectionTracker = ADSRTracker(globalThis)
    val thisPeakAttackLv = 50.0
    val thisSustainTime = 5000
    var thisRect = Rectangle(_x, _y, _w, _h)
    init {
        sectionTracker.attack = 0.1
        sectionTracker.decay = 0.05
        sectionTracker.sustain = 0.9
        sectionTracker.release = 0.1
    }
//    val outer = thisRect.offsetEdges(thisRect.width * 0.1, thisRect.height * 0.1)
    var isSelected = false
    var isWithin = false
    var isWithinPrev = false
    var origin = Vector2(0.0, 0.0)
    var isTriggerActive = false
    fun check() {
//        if(Selector.currentSection == id){
//            isSelected = true
//        }
    }

    fun getDist(Mouse: Mouse) {
        isWithin = thisRect.contains(Mouse.pos)
        if (isWithinPrev != isWithin) {
            println(isWithin)
            isWithinPrev = isWithin
            if(!isWithin && isTriggerActive) {
                sectionTracker.triggerOff()
                isTriggerActive = false
            }
            if(isWithin && !isTriggerActive) {
                sectionTracker.triggerOn()
                isTriggerActive = true
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
        }
        drawer.circle(thisRect.corner, sectionTracker.value() * thisPeakAttackLv)
//        if (isSelected) drawer.rectangle(thisRect)
    }
}

