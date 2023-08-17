
import Global.height
import Global.width
import classes.ParagraphStyle
import demos.classes.Animation
import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.draw.FontImageMap
import org.openrndr.draw.loadFont
import org.openrndr.draw.loadImage
import org.openrndr.extra.envelopes.ADSRTracker
import org.openrndr.extra.shapes.RoundedRectangle
import org.openrndr.math.Vector2
import org.openrndr.svg.loadSVG
import java.io.File
import kotlin.properties.Delegates

object Global {
    lateinit var globalThis: Program
    lateinit var tracker: ADSRTracker
    lateinit var drawer: Drawer
    val animation = Animation()
    var width by Delegates.notNull<Int>()
    var height by Delegates.notNull<Int>()
    var frameCount by Delegates.notNull<Int>()
    var clock by Delegates.notNull<Double>()
    var selection = 4
    var prevSelection = 4
    var numSections = 0

    lateinit var monoReg: FontImageMap
    lateinit var monoBold2: FontImageMap
    lateinit var monoBold: FontImageMap
    lateinit var maruMini: FontImageMap
    lateinit var vecList: List<Vector2>
    lateinit var h3: ParagraphStyle
    lateinit var h32: ParagraphStyle
    lateinit var h31: ParagraphStyle
    lateinit var h1: ParagraphStyle
}

object Selector {
    fun check(_randomNum: Int) {
        if(_randomNum != Global.selection){
            println("State Changed")
            Global.selection = _randomNum
            println(sections.size)
            sections[Global.selection].intro()
        }
    }
}

object Mouse {
    var pos = Vector2(0.0, 0.0)
    val rad = 3.0
}

object LinePath {
    val margin = 10.0
    var outline: RoundedRectangle = RoundedRectangle(
        margin,
        margin,
        width - (margin*2.0),
        height - (margin*2.0),
        10.0
    )
}