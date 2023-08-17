

import org.openrndr.KEY_SPACEBAR
import org.openrndr.Program
import org.openrndr.extra.noise.random


fun Program.setupKeyboardListeners() {
    keyboard.keyDown.listen { event ->
        if (event.key == KEY_SPACEBAR) {
            Selector.updateSelection()
        }
    }
}

