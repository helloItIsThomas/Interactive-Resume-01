
package classes
import Global.height
import Global.width
import org.openrndr.extra.keyframer.Keyframer
import org.openrndr.math.Vector2
import org.openrndr.math.mix

class MrLine : Keyframer() {
    var core = 0.0
    val clockDiv = 0.01
    var decTime = 0.0
    var intTime = 0
    var fracTime = 0.0

    val lineSlider0 by DoubleChannel("pathSlider")
    var startPos = Vector2(width * 0.12, height * 0.15)
    var endPos = Vector2((width*0.32) + (width * 0.563), height * 0.15)
    var drawPos = startPos

    val stops = mutableListOf(
        0.85,
        0.277,
        0.342,
        0.8,
        0.83,
        0.8478,
        0.87
    )
    var currentPos = 0.0
    var newPos = 0.0

    fun update(){
        core *= clockDiv
        decTime = core
        intTime = core.toInt()
        fracTime = core % 1.0
    }

    fun seek() {
//        newPos = mix(currentPos, stops[Global.selection], lineSlider0)
    }

    fun check() {
        this.seek()
    }
}