

import Global.MrLine0
import org.openrndr.KEY_SPACEBAR
import org.openrndr.MouseEvents
import org.openrndr.Program


fun Program.setupKeyboardListeners() {
    keyboard.keyDown.listen { event ->
        if (event.key == KEY_SPACEBAR) {
            Selector.updateSelection()
        }
    }
}


