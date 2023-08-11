

import org.openrndr.KEY_SPACEBAR
import org.openrndr.Program
import org.openrndr.extra.noise.random


fun Program.setupKeyboardListeners() {
    keyboard.keyDown.listen { event ->
        if (event.key == KEY_SPACEBAR) {
            val randomNumber = random(0.0, 5.0)
            Selector.check(randomNumber)
            sections[4].sectionTracker.triggerOn()
            sections[4].isTriggerActive = true
        }
    }
}

