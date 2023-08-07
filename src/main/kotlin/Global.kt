
import Global.height
import Global.width
import classes.ParagraphStyle
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.draw.loadFont
import org.openrndr.draw.loadImage
import org.openrndr.extra.shapes.RoundedRectangle
import org.openrndr.math.Vector2
import java.io.File

public object Global {
    lateinit var drawer: Drawer
    var width: Int = 100
    var height: Int = 100
    var frameCount: Int = 1

    val monoReg = loadFont(("data/fonts/mono-reg.otf"), width*0.013)
    val monoBold2 = loadFont(("data/fonts/mono-bold.otf"), width*0.013)
    val monoBold = loadFont(("data/fonts/mono-bold.otf"), width*0.0193)
    val maruMini = loadFont(("data/fonts/maru-mini.otf"), width*0.033)
    val resumeImg = loadImage(File("data/images/resume.jpg"))
    var vecList = listOf(
        Vector2(306.888437030501, 200.0),
    )
    val h3 = ParagraphStyle(monoReg, ColorRGBa.BLACK, 1.2)
    val h32 = ParagraphStyle(monoBold, ColorRGBa.BLACK, 1.2)
    val h31 = ParagraphStyle(monoBold2, ColorRGBa.BLACK, 1.2)
    val h1 = ParagraphStyle(maruMini, ColorRGBa.BLACK, 0.1)
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
