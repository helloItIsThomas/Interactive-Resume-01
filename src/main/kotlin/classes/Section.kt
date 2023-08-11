package classes

import Global.drawer
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
import org.openrndr.math.clamp
import org.openrndr.math.map
import org.openrndr.shape.Rectangle
import java.util.Vector
import kotlin.math.sqrt

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
    var thisInner = Rectangle(_x, _y, _w, _h)
    var varDecay = 0.05
    init {
        sectionTracker.attack = 0.1
        sectionTracker.decay = 0.05
        sectionTracker.sustain = 0.9
        sectionTracker.release = 0.1
    }
    val thisOuter = thisInner.offsetEdges(thisInner.width * 0.1, thisInner.height * 0.1)
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
        isWithin = thisInner.contains(Mouse.pos)
        isWithinOuter = thisOuter.contains(Mouse.pos)

        val widthOffset = thisInner.width * 0.1
        val heightOffset = thisInner.height * 0.1
        val maxDistance = sqrt((widthOffset * widthOffset) + (heightOffset * heightOffset))

        val closestXInner = Mouse.pos.x.clamp(thisInner.x, thisInner.x + thisInner.width)
        val closestYInner = Mouse.pos.y.clamp(thisInner.y, thisInner.y + thisInner.height)
        val closestPointInner = Vector2(closestXInner, closestYInner)

        val closestXOuter = Mouse.pos.x.clamp(thisOuter.x, thisOuter.x + thisOuter.width)
        val closestYOuter = Mouse.pos.y.clamp(thisOuter.y, thisOuter.y + thisOuter.height)
        val closestPointOuter = Vector2(closestXOuter, closestYOuter)

        val deleteMeRaw = (closestPointOuter - closestPointInner).length
        val mappedDistance = deleteMeRaw / maxDistance // This will map deleteMe from 0.0 to 1.0


        varDecay = mappedDistance.map(0.0, 1.0, 1.0, 0.0)

//        println( sectionTracker.release )
        println( varDecay )

        if(isWithinOuter && !isWithin){
            if (isWithinOuterPrev != isWithinOuter) {
                // Implement
                // varDecay = distance from any edge of innerRect to mouse,
                // and scale it to between 0.0 and 1.0
            }
        }
        drawer.strokeWeight = 1.0
        drawer.lineSegment(closestPointInner, closestPointOuter)

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
        thisInner = Rectangle(
            _x,
            _y,
            _w, // + sectionTracker.value() * thisPeakAttackLv,
            _h
        )
        drawer.stroke = ColorRGBa.BLACK
        drawer.fill = null
        if (this.id == 4){
            drawer.rectangle(thisInner)
//            drawer.rectangle(thisOuter)
        }
        drawer.circle(thisInner.corner, sectionTracker.value() * thisPeakAttackLv)
//        if (isSelected) drawer.rectangle(thisInner)
    }
}

