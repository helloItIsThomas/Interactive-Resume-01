package classes

import Mouse
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
    val outer = thisRect.offsetEdges(thisRect.width * 0.1, thisRect.height * 0.1)
    var isSelected = false
    var origin = Vector2(0.0, 0.0)
    fun check() {
//        if(Selector.currentSection == id){
//            isSelected = true
//        }
    }

    fun getDist(Mouse: Mouse) {
        val isWithin = thisRect.contains(Mouse.pos)
        val isClose = outer.contains(Mouse.pos)

        val i = thisRect
        val p = Mouse.pos
        val result = if (outer.contains(p)) minOf(
            1.0,
            maxOf(
                0.0,
                minOf(
                    (p.x - outer.x) / (i.x - outer.x),
                    (outer.x + outer.width - p.x) / (outer.x + outer.width - i.x - i.width),
                    (p.y - outer.y) / (i.y - outer.y),
                    (outer.y + outer.height - p.y) / (outer.y + outer.height - i.y - i.height)
                )
            )
        ) else 0.0

        // at the moment we are doing something wrong
        // because we don't know exactly what we are trying to achieve.
        // take a break and then look at this phase handling  with fresh eyes.

        isSelected = isWithin
        this.move(result)
    }

    fun move(result: Double) {
//        println(Global.clock * result)
        this.phaseAmt = (Global.clock * (result)).map(
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
        if (this.id == 4){
            drawer.rectangle(thisRect)
            drawer.rectangle(outer)
        }
//        if (isSelected) drawer.rectangle(thisRect)
    }
}

