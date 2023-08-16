package classes

import Global.globalThis
import Mouse
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.color.presets.FOREST_GREEN
import org.openrndr.extra.envelopes.ADSRTracker
import org.openrndr.extra.noise.random
import org.openrndr.math.Vector2
import org.openrndr.shape.Circle
import org.openrndr.shape.Rectangle
import kotlin.properties.Delegates

class Section(
    val id: Int,
    var phaseAmt: Double,
    var _x: Double,
    var _y: Double,
    var _w: Double,
    var _h: Double,
    val allTxt: MutableList<CustomText>
) {
    var sectionTracker = ADSRTracker(globalThis)
    val thisPeakAttackLv = 50.0
    val thisSustainTime = 5000
    var thisRect = Rectangle(_x, _y, _w, _h)
    var varDecay = 0.05

    var wordPos = mutableListOf<Vector2>()

    var parentNumWords = mutableListOf<Int>()
    // we are never not drawing all the text in a given section at once.
    // so we should be able to have one numWords variable.
    var numWords = 0
    var numChars by Delegates.notNull<Int>()
    var tempGuideCirc: Circle

    init {
        this.allTxt.forEach { t ->
            val words: List<String> = t.txtStr.split(" ")
            // so I think we fixed it by doing this +=,
            // because now it should be adding the words from each sub-section
            // ( sub-section meaning the part that determines what styling to set the text with )
            // together into a variable numWords that represents the number of all words in the section.
//            numWords += words.size
            // ok lets try it the other way.
            parentNumWords.add(words.size)
            // The above holds a list of integers
            // that represents the number of words in each sub-section
            // for this section.
            // so, if this.id == 4,
            // then a println would return [1, 3, 15, 8],
            // representing 4 distinct sub-sections to change the drawer to 4 different styles,
            // with 1 word in the first sub-section, 3 words in the second, etc.
        }
        if(this.id == 4){
            println(parentNumWords)
        }

        tempGuideCirc = Circle(_x + (_w*0.5), _y + (_h*0.5), _w*0.8)
        wordPos = tempGuideCirc.contour.equidistantPositions(numWords).toMutableList()

        sectionTracker.attack = 1.1
        sectionTracker.decay = 0.05
        sectionTracker.sustain = 0.9
        sectionTracker.release = 0.075
    }

    val thisOuter = thisRect.offsetEdges(thisRect.width * 0.1, thisRect.height * 0.1)
    var isSelected = false
    var isWithin = false
    var isWithinOuter = false
    var isWithinPrev = false
    var isWithinOuterPrev = false
    var origin = Vector2(0.0, 0.0)
    var isTriggerActive = false

    fun check() {
//        println("wordPos.size for " +  this.id + " is " + wordPos.size)
//        println("numWords count for " +  this.id + " is " + numWords)
    }

    fun getDist(Mouse: Mouse) {
        isWithin = thisRect.contains(Mouse.pos)
        if(isWithin != isWithinPrev){
            if(isWithin){
                isWithinPrev = isWithin
                sectionTracker.triggerOn()
            } else{
                isWithinPrev = isWithin
                sectionTracker.triggerOff()
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
            drawer.rectangle(thisOuter)
//            println(sectionTracker.value())
        }
        drawer.circle(thisRect.corner, sectionTracker.value() * thisPeakAttackLv)
//        if (isSelected) drawer.rectangle(thisRect)
    }
}

