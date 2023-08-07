package classes

import Selector
import org.openrndr.draw.Drawer
import org.openrndr.shape.Rectangle

class Section(
    val id: Int,
    var hype: Double,
    _x: Double,
    _y: Double,
    _w: Double,
    _h: Double,
    val txt: MutableList<CustomText>
) {
    var thisRect = Rectangle(_x, _y, _w, _h)
    var isSelected = false
    fun check(){
        if(Selector.currentSection == id){
            isSelected = true
        }
    }
    fun render(drawer: Drawer){
//                drawer.fill = ColorRGBa.FOREST_GREEN
//                if(isSelected) drawer.rectangle(thisRect)
    }
}