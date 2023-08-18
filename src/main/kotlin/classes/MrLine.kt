
package classes
import Global.drawer
import Global.frameCount
import Global.height
import Global.width
import Mouse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.openrndr.extra.keyframer.Keyframer
import org.openrndr.extra.timeoperators.Envelope
import org.openrndr.math.Vector2
import org.openrndr.math.map
import org.openrndr.math.mix
import org.openrndr.shape.ShapeContour

class MrLine : Keyframer() {
    var core = 0.0
    val clockDiv = 0.01
    var decTime = 0.0
    var intTime = 0
    var fracTime = 0.0
    var mrLinePath: ShapeContour = LinePath.outline.contour

    //
    var mrLineControl = 0.5
    //

    val lineSlider0 by DoubleChannel("pathSlider")
    var startPos = Vector2(width * 0.12, height * 0.15)
    var drawPos = startPos
    var drawPosT: Double = 0.0
    var targetPos = Vector2(0.0, 0.0)

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

        drawPos = mrLinePath.position(drawPosT)

    }

    fun seek(target: Vector2) {
        var startPosT = mrLinePath.nearest(startPos).contourT
        var targetPosT = mrLinePath.nearest(target).contourT
        val durationInSeconds = 1.0 // Adjust the duration as needed
        val size = Envelope(50.0, 400.0, 0.5, 0.5)
    }


}