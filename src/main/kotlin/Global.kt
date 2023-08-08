
import Global.height
import Global.width
import classes.ParagraphStyle
import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.draw.FontImageMap
import org.openrndr.draw.loadFont
import org.openrndr.draw.loadImage
import org.openrndr.extra.shapes.RoundedRectangle
import org.openrndr.math.Vector2
import java.io.File
import kotlin.properties.Delegates

public object Global {
    lateinit var drawer: Drawer
    var width by Delegates.notNull<Int>()
    var height by Delegates.notNull<Int>()
    var frameCount by Delegates.notNull<Int>()

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

public object Selector {
    var currentSection = 0
    fun check(_randomNum: Double) {
        if(_randomNum.toInt() != currentSection){
            println("State Changed")
            currentSection = _randomNum.toInt()
        }
    }
}

public object LinePath {
    val margin = 10.0
    var outline: RoundedRectangle = RoundedRectangle(
        margin,
        margin,
        width - (margin*2.0),
        height - (margin*2.0),
        10.0
    )
}
