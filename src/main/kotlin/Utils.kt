

import org.openrndr.KEY_SPACEBAR
import org.openrndr.Program
import org.openrndr.extra.noise.random


fun Program.setupKeyboardListeners() {
    keyboard.keyDown.listen { event ->
        if (event.key == KEY_SPACEBAR) {
            sendRandomInt()
        }
    }
}

fun sendRandomInt(){
    val randomNumber = random(1.0, Global.numSections.toDouble()).toInt()
    println(randomNumber)
    Selector.check(randomNumber)
}

