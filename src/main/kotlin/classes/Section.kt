package classes

import Mouse
import Selector
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.color.presets.FOREST_GREEN
import org.openrndr.math.Vector2
import org.openrndr.math.map
import org.openrndr.shape.Rectangle

class Section(
    val id: Int,
    var phaseAmt: Double,
    _x: Double,
    _y: Double,
    _w: Double,
    _h: Double,
    val txt: MutableList<CustomText>
) {
    var thisRect = Rectangle(_x, _y, _w, _h)
    var isSelected = false
    var origin = Vector2(0.0, 0.0)
    fun check() {
//        if(Selector.currentSection == id){
//            isSelected = true
//        }
    }

    fun getDist(Mouse: Mouse) {
        val isWithin = thisRect.contains(Mouse.pos)
        val outer = thisRect.offsetEdges(thisRect.width * 0.25, thisRect.height * 0.25)
        val isClose = outer.contains(Mouse.pos)
        isSelected = isWithin
    }

    fun move(){
        this.phaseAmt = (Global.clock).map(
            0.0,
            10.0,
            0.0,
            1.0,
            true
        )
    }

    fun render(drawer: Drawer) {
        drawer.stroke = ColorRGBa.FOREST_GREEN
        drawer.fill = null
        if (isSelected) drawer.rectangle(thisRect)
    }
}

